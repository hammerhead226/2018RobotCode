package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToPower extends Command {

	public ElevatorToPower() {
		requires(Robot.elevator);
		setTimeout(Constants.ELEVATOR_ON_TARGET_S);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.moveElevatorToPower();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.elevator.isFinished(Constants.ELEVATOR_POW_HEIGHT) || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.updateCurrentPosition();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
