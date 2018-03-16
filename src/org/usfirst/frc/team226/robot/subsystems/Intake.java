package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.DriveIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private TalonSRX left = new TalonSRX(RobotMap.INTAKE_LEFT);
	private TalonSRX right = new TalonSRX(RobotMap.INTAKE_RIGHT);
	private TalonSRX roller = new TalonSRX(RobotMap.INTAKE_ROLLERS);

	public Intake() {
		left.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);
		right.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);
		roller.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);

		left.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		roller.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);
		right.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);
		roller.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);

		left.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
		roller.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.INTAKE_INVERT_L);
		right.setInverted(Constants.INTAKE_INVERT_R);
		roller.setInverted(Constants.ROLLER_INVERT);
		
		right.follow(left);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveIntake());
	}

	public void neutralOutput() {
		left.neutralOutput();
	}
	
	public void driveIntake(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}
	
	public void driveRollers(double speed) {
		roller.set(ControlMode.PercentOutput, speed);
	}
	
	public void rollerNeutral() {
		roller.set(ControlMode.PercentOutput, 0);
	}
}
