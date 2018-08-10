package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.C_DriveClimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private TalonSRX left = new TalonSRX(RobotMap.CLIMBER_LEFT);
	private TalonSRX right = new TalonSRX(RobotMap.CLIMBER_RIGHT);
	private Servo lock = new Servo(RobotMap.CLIMBER_SERVO);

	public Climber() {
		left.configContinuousCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
		right.configContinuousCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);

		left.enableCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.CLIMBER_VOLTAGE_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
		right.configVoltageCompSaturation(Constants.CLIMBER_VOLTAGE_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);

		left.enableVoltageCompensation(Constants.CLIMBER_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.CLIMBER_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.CLIMBER_INVERT_L);
		right.setInverted(Constants.CLIMBER_INVERT_R);

		lock.set(Constants.LOCKED_POSITION);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new C_DriveClimber());
	}

	public void driveClimber(double speed) {
		if(speed == 0) {
			speed = -0.05;
		}
		left.set(ControlMode.PercentOutput, speed);
		right.set(ControlMode.PercentOutput, speed);
	}

	public void activateClimber() {
		lock.set(Constants.UNLOCKED_POSITION);
	}

}
