package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.W_DriveWrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

	private TalonSRX left = new TalonSRX(RobotMap.WRIST_LEFT);
	private TalonSRX right = new TalonSRX(RobotMap.WRIST_RIGHT);
	private int position = 0;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Wrist() {
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.WRIST_PIDSLOT_IDX, Constants.WRIST_TIMEOUT);
		
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

		right.follow(left);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new W_DriveWrist());
	}
	
	public void hardZeroEncoder() {
		left.setSelectedSensorPosition(0, Constants.WRIST_PIDSLOT_IDX, Constants.WRIST_TIMEOUT);
	}

	public void driveWrist(double speed) {
		if (speed == 0) {
			left.set(ControlMode.Position, position);
		}
		left.set(ControlMode.PercentOutput, speed);
		updatePos();
	}
	
	public void updatePos() {
		position = left.getSelectedSensorPosition(Constants.WRIST_PIDSLOT_IDX);
	}
	
	public void neutralOutput() {
		left.neutralOutput();
	}
}
