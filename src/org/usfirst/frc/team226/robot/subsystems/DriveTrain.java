package org.usfirst.frc.team226.robot.subsystems;

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
	
	public DriveTrain() {
		frontLeft.setInverted(true);
		centerLeft.setInverted(false);
		rearLeft.setInverted(true);

		frontRight.setInverted(false);
		centerRight.setInverted(false);
		rearRight.setInverted(true);

		centerLeft.follow(frontLeft);
		centerRight.follow(frontRight);
		rearLeft.follow(frontLeft);
		rearRight.follow(frontRight);
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new CheesyDrive());
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		double leftMotorSpeed;
		double rightMotorSpeed;

		rotateValue = limit(rotateValue);
		moveValue = limit(moveValue);

		// Square inputs
		if (rotateValue >= 0.0) {
			rotateValue = rotateValue * rotateValue;
		} else {
			rotateValue = -(rotateValue * rotateValue);
		}
		if (moveValue >= 0.0) {
			moveValue = moveValue * moveValue;
		} else {
			moveValue = -(moveValue * moveValue);
		}

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

	protected double limit(double value) {
		if (value > 1.0) {
			return 1.0;
		}
		if (value < -1.0) {
			return -1.0;
		}
		return value;
	}

}
