package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.W_DriveWrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Wrist extends Subsystem {

	private TalonSRX left = new TalonSRX(RobotMap.WRIST_LEFT);
	private TalonSRX right = new TalonSRX(RobotMap.WRIST_RIGHT);
	private int position = -920;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Wrist() {
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.WRIST_PIDSLOT_IDX,
				Constants.WRIST_TIMEOUT);

		left.configContinuousCurrentLimit(Constants.WRIST_CURRENT_LIMIT, Constants.WRIST_TIMEOUT);
		right.configContinuousCurrentLimit(Constants.WRIST_CURRENT_LIMIT, Constants.WRIST_TIMEOUT);

		left.enableCurrentLimit(Constants.WRIST_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.WRIST_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.WRIST_VOLTAGE_LIMIT, Constants.WRIST_TIMEOUT);
		right.configVoltageCompSaturation(Constants.WRIST_VOLTAGE_LIMIT, Constants.WRIST_TIMEOUT);

		left.enableVoltageCompensation(Constants.WRIST_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.WRIST_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.WRIST_INVERT_L);
		right.setInverted(Constants.WRIST_INVERT_R);
		
		hardZeroEncoder();

		right.follow(left);
	}

	public void log() {
		SmartDashboard.putNumber("wrist relative pos", left.getSelectedSensorPosition(Constants.WRIST_PIDSLOT_IDX));
		SmartDashboard.putNumber("wrist absolute pos", left.getSensorCollection().getPulseWidthPosition());
		SmartDashboard.putNumber("wrist position", position);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new W_DriveWrist());
	}

	public void hardZeroEncoder() {
		left.setSelectedSensorPosition(0, Constants.WRIST_PIDSLOT_IDX, Constants.WRIST_TIMEOUT);
		position = 0;
	}

	public void driveWrist(double speed) {
		if(position <= -530 && speed <= 0) {
			speed = 0;
		}
		if (speed == 0) {
			left.set(ControlMode.Position, position);
		} else {
			left.set(ControlMode.PercentOutput, speed * 0.5);
			updatePos();
		}
	}

	public void updatePos() {
		position = left.getSelectedSensorPosition(Constants.WRIST_PIDSLOT_IDX);
	}

	public void neutralOutput() {
		left.neutralOutput();
	}
}
