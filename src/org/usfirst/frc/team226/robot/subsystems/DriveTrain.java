package org.usfirst.frc.team226.robot.subsystems;

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

	public void tankDrive(double left, double right) {
		frontLeft.set(ControlMode.PercentOutput, -limit(left));
		frontRight.set(ControlMode.PercentOutput, limit(right));
	}

	private double limit(double value) {
		if (value > 1.0) {
			return 1.0;
		}
		if (value < -1.0) {
			return -1.0;
		}
		return value;
	}

}
