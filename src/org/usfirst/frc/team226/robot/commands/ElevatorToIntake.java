package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToIntake extends Command {

	public ElevatorToIntake() {
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.moveElevatorToIntake();
		setTimeout(Constants.ELEVATOR_ON_TARGET_S);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {	
		return Robot.elevator.isFinished(Constants.ELEVATOR_INTAKE_HEIGHT) || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}
