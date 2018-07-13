package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.E_ControlElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private TalonSRX left1 = new TalonSRX(RobotMap.ELEVATOR_LEFT_1);
	private TalonSRX left2 = new TalonSRX(RobotMap.ELEVATOR_LEFT_2);
	private TalonSRX right1 = new TalonSRX(RobotMap.ELEVATOR_RIGHT_1);
	private TalonSRX right2 = new TalonSRX(RobotMap.ELEVATOR_RIGHT_2);

	private int setpointPosition = ElevatorSetpoint.GROUND.position;

	public Elevator() {

		left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PIDSLOT_IDX,
				Constants.ELEVATOR_TIMEOUT);

		left1.setSensorPhase(Constants.ELEVATOR_SENSOR_PHASE);

		left1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		left2.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		right1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		right2.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);

		left1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		left2.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right2.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);

		left1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		left2.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		right1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		right2.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);

		left1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		left2.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right2.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		left1.setInverted(Constants.ELEVATOR_INVERT_L);
		left2.setInverted(Constants.ELEVATOR_INVERT_L);
		right1.setInverted(Constants.ELEVATOR_INVERT_R);
		right2.setInverted(Constants.ELEVATOR_INVERT_R);

		left1.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		left2.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		right1.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		right2.configReverseSoftLimitEnable(Constants.ELEVATOR_REVERSE_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);

		left2.follow(left1);
		right1.follow(left1);
		right2.follow(left1);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new E_ControlElevator());
	}

	public enum ElevatorSetpoint {
		// setpoints to be set next week
		GROUND(0), EXCHANGE(0), SWITCH(0), SCALE(0);
		public int position;

		private ElevatorSetpoint(int position) {
			this.position = position;
		}
	}

	public void setElevatorSetpoint(ElevatorSetpoint s) {
		setpointPosition = s.position;
	}

	public int getElevatorPos() {
		return left1.getSelectedSensorPosition(Constants.ELEVATOR_PIDSLOT_IDX);
	}

	public void hardZeroEncoder() {
		left1.setSelectedSensorPosition(0, Constants.ELEVATOR_PIDSLOT_IDX, Constants.ELEVATOR_TIMEOUT);
	}

	public void driveElevator(double speed) {
		left1.set(ControlMode.PercentOutput, speed);
	}

	public void controlElevator(double speed) {
		if (speed != 0) {
			left1.set(ControlMode.PercentOutput, speed);
			setpointPosition = getElevatorPos();
		} else {
			left1.set(ControlMode.Position, setpointPosition);
		}
		
		SmartDashboard.putNumber("11", left1.getOutputCurrent());
		SmartDashboard.putNumber("14", left1.getOutputCurrent());
		SmartDashboard.putNumber("15", left1.getOutputCurrent());
		SmartDashboard.putNumber("21", left1.getOutputCurrent());

	}

}
