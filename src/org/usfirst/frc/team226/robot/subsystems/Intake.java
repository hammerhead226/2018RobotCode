package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.TeleopCarriage;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	TalonSRX left = new TalonSRX(RobotMap.INTAKE_LEFT_ID);
	TalonSRX right = new TalonSRX(RobotMap.INTAKE_RIGHT_ID);

	public Intake() {

		left.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_SENSOR_TIMEOUT);
		right.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_SENSOR_TIMEOUT);

		left.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_SENSOR_TIMEOUT);
		right.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_SENSOR_TIMEOUT);

		left.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.INTAKE_INVERT_L);
		right.setInverted(Constants.CARRIAGE_INVERT_R);
		
		right.follow(left);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopCarriage());
	}

	public void intakeNeutral() {
		left.neutralOutput();
	}
	
	public void setIntakeSpeed() {
		left.set(ControlMode.PercentOutput, Robot.oi.manip.getTriggers());
	}
}
