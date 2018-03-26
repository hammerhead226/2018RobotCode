package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.A_ControlArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

	private TalonSRX right = new TalonSRX(RobotMap.ARM_RIGHT);
	private TalonSRX left = new TalonSRX(RobotMap.ARM_LEFT);

	public void log() {
		SmartDashboard.putNumber("Arm Left", left.getOutputCurrent());
		SmartDashboard.putNumber("Arm Right", right.getOutputCurrent());
	}

	private int setpointPosition = ArmSetpoint.STRAIGHT_UP.position;

	public Arm() {
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ARM_PIDSLOT_IDX,
				Constants.ARM_TIMEOUT);
		right.setSensorPhase(Constants.ARM_SENSOR_PHASE);

		hardZeroEncoder();

		right.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);
		left.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);

		right.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
		left.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);

		right.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);
		left.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);

		right.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
		left.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);

		right.setInverted(Constants.ARM_INVERT_R);
		left.setInverted(Constants.ARM_INVERT_L);

		right.configForwardSoftLimitEnable(Constants.ARM_FORWARD_LIMIT_ENABLED, Constants.ARM_TIMEOUT);
		right.configReverseSoftLimitEnable(Constants.ARM_REVERSE_LIMIT_ENABLED, Constants.ARM_TIMEOUT);
		left.configForwardSoftLimitEnable(Constants.ARM_FORWARD_LIMIT_ENABLED, Constants.ARM_TIMEOUT);
		left.configReverseSoftLimitEnable(Constants.ARM_REVERSE_LIMIT_ENABLED, Constants.ARM_TIMEOUT);

		right.configPeakOutputForward(Constants.ARM_MAX_SPEED, Constants.ARM_TIMEOUT);
		right.configPeakOutputReverse(-Constants.ARM_MAX_SPEED, Constants.ARM_TIMEOUT);

		left.follow(right);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new A_ControlArm());
	}

	public enum ArmSetpoint {
		FRONT_SCALE(3300), STRAIGHT_UP(3115), BACK_SCALE(2850), BACK_SWITCH(1850), BACK_GROUND(0);

		public int position;

		private ArmSetpoint(int position) {
			this.position = position;
		}

	}

	public void setArmSetpoint(ArmSetpoint sp) {
		this.setpointPosition = sp.position;
	}

	public int getArmPos() {
		return right.getSelectedSensorPosition(Constants.ARM_PIDSLOT_IDX);
	}

	public void hardZeroEncoder() {
		right.getSensorCollection().setPulseWidthPosition(0, Constants.ARM_TIMEOUT);
		right.setSelectedSensorPosition(0, 0, Constants.ARM_TIMEOUT);
	}

	public int getArmError() {
		return right.getClosedLoopError(Constants.ARM_PIDSLOT_IDX);
	}

	public void driveArm(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}

	public void controlArm(double speed) {
		if (speed != 0) {
			right.set(ControlMode.PercentOutput, softLimit(speed));
			setpointPosition = getArmPos();
		} else {
			right.set(ControlMode.Position, setpointPosition);
		}
	}

	private double softLimit(double input) {
		if (getArmPos() >= ArmSetpoint.STRAIGHT_UP.position + Constants.ARM_ERROR_TOLERANCE) {
			if (input > 0) {
				input = 0;
			}
		} else if (getArmPos() <= ArmSetpoint.BACK_GROUND.position - Constants.ARM_ERROR_TOLERANCE) {
			if (input < 0) {
				input = 0;
			}
		}
		return input;
	}

}
