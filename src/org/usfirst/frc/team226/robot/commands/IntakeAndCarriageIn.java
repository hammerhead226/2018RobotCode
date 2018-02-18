package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAndCarriageIn extends Command {

	public IntakeAndCarriageIn() {
		requires(Robot.elevator);
		requires(Robot.carriage);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.elevator.getElevatorHeight() < Constants.ELEVATOR_INTAKE_TOLERANCE && Robot.carriage.photoEyeOpen()) {
			Robot.intake.intake(Robot.m_oi.driver.getLeftTrigger(), Robot.m_oi.driver.getRightTrigger());
			Robot.carriage.inOrOut((Robot.m_oi.driver.getLeftTrigger() + Robot.m_oi.driver.getRightTrigger()) / 2);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
