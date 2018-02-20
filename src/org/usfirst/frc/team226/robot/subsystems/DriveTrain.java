package org.usfirst.frc.team226.robot.subsystems;

import org.hammerhead226.sharkmacro.actions.ActionListParser;
import org.hammerhead226.sharkmacro.actions.ActionRecorder;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileRecorder;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileRecorder.RecordingType;
import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.CheesyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private TalonSRX frontLeft = new TalonSRX(RobotMap.DT_FRONT_LEFT);
	private TalonSRX frontRight = new TalonSRX(RobotMap.DT_FRONT_RIGHT);
	private TalonSRX centerLeft = new TalonSRX(RobotMap.DT_CENTER_LEFT);
	private TalonSRX centerRight = new TalonSRX(RobotMap.DT_CENTER_RIGHT);
	private TalonSRX rearLeft = new TalonSRX(RobotMap.DT_REAR_LEFT);
	private TalonSRX rearRight = new TalonSRX(RobotMap.DT_REAR_RIGHT);
	
	private ProfileRecorder recorder = new ProfileRecorder(frontLeft, frontRight, RecordingType.VOLTAGE);

	public DriveTrain() {
		frontLeft.setInverted(true);
		centerLeft.setInverted(false);
		rearLeft.setInverted(true);

		frontRight.setInverted(true);
		centerRight.setInverted(false);
		rearRight.setInverted(true);

		centerLeft.follow(frontLeft);
		centerRight.follow(frontRight);
		rearLeft.follow(frontLeft);
		rearRight.follow(frontRight);

		frontLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);
		centerLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);
		rearLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);
		frontRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);
		centerRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);
		rearRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.STARTUP_WAIT);

		frontLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
		centerLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
		rearLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
		frontRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
		centerRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
		rearRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);

		frontLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);
		centerLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);
		rearLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);
		frontRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);
		centerRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);
		rearRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.STARTUP_WAIT);

		frontLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
		centerLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
		rearLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
		frontRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
		centerRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
		rearRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);

	}
	
	public void toggleProfileRecording() {
		if (recorder.isRecording()) {
			ProfileParser p = new ProfileParser(ProfileParser.getNewFilename());
			p.writeToFile(recorder.stop().toProfile());
			System.out.println("Profile saved.");
		} else {
			zeroEncoders();
			System.out.println("Profile recording started.");
			recorder.start();
		}
	}
	
	public void toggleActionListRecording() {
		if (ActionRecorder.isRecording()) {
			ActionListParser al = new ActionListParser(ActionListParser.getNewFilename());
			al.writeToFile(ActionRecorder.stop());
			System.out.println("ActionList saved.");
		} else {
			ActionRecorder.start();
			System.out.println("ActionList recording started.");
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new CheesyDrive());
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		double leftMotorSpeed;
		double rightMotorSpeed;

		// Square inputs
		moveValue = Math.copySign(moveValue * moveValue, moveValue);
		rotateValue = Math.copySign(rotateValue * rotateValue, rotateValue);

		if (rotateValue > 0.0) {
			if (moveValue > 0.0) {
				leftMotorSpeed = rotateValue - moveValue;
				rightMotorSpeed = Math.max(rotateValue, moveValue);
			} else {
				leftMotorSpeed = Math.max(rotateValue, -moveValue);
				rightMotorSpeed = rotateValue + moveValue;
			}
		} else {
			if (moveValue > 0.0) {
				leftMotorSpeed = -Math.max(-rotateValue, moveValue);
				rightMotorSpeed = rotateValue + moveValue;
			} else {
				leftMotorSpeed = rotateValue - moveValue;
				rightMotorSpeed = -Math.max(-rotateValue, -moveValue);
			}
		}
		frontLeft.set(ControlMode.PercentOutput, leftMotorSpeed);
		frontRight.set(ControlMode.PercentOutput, rightMotorSpeed);
	}

	public void tankDrive(double left, double right) {
		frontLeft.set(ControlMode.PercentOutput, left);
		frontRight.set(ControlMode.PercentOutput, right);
	}

	public TalonSRX[] getMotionProfileTalons() {
		return new TalonSRX[] { frontLeft, frontRight };
	}
	
	private void zeroEncoders() {
		frontLeft.setSelectedSensorPosition(0, 0, 0);
		frontRight.setSelectedSensorPosition(0, 0, 0);
		System.out.println("Drivetrain encoders zeroed.");
	}

}
