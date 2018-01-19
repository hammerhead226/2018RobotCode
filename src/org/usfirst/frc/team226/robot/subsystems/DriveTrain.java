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
	
	public TalonSRX frontLeft = new TalonSRX(RobotMap.FRONT_LEFT);
	public TalonSRX frontRight = new TalonSRX(RobotMap.FRONT_RIGHT);
	public TalonSRX centerLeft = new TalonSRX(RobotMap.CENTER_LEFT);
	public TalonSRX centerRight = new TalonSRX(RobotMap.CENTER_RIGHT);
	public TalonSRX backLeft = new TalonSRX(RobotMap.BACK_LEFT);
	public TalonSRX backRight = new TalonSRX(RobotMap.BACK_RIGHT);
	
	public DriveTrain() {
		centerLeft.follow(frontLeft);
		centerRight.follow(frontRight);
		backLeft.follow(frontLeft);
		backRight.follow(frontRight);
	}
	
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
		double leftMotorSpeed;
		double rightMotorSpeed;

		rotateValue = limit(rotateValue);
		moveValue = limit(moveValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control
			// while permitting full power
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

	public void initDefaultCommand() {
		// setDefaultCommand(new ArcadeDrive());
		// Set the default command for a subsystem here.
		setDefaultCommand(new CheesyDrive());
	}

}
