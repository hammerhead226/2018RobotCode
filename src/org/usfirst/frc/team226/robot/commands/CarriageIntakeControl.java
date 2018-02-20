package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarriageIntakeControl extends Command {

	public CarriageIntakeControl() {
		requires(Robot.carriageIntake);
}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.carriageIntake.intakeNeutral();
		Robot.carriageIntake.carriageNeutral();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.carriageIntake.runCarriageAndIntake(Robot.elevatorHeight);
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
