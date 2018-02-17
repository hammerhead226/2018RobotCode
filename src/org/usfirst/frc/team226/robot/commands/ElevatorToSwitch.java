package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToSwitch extends Command {

	long timeout = System.currentTimeMillis();

	public ElevatorToSwitch() {
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.moveElevatorToSwitch();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.elevator.getElevatorError() < Constants.ELEVATOR_ERROR_MARGIN) {
			return true;
		} else {
			if ((System.currentTimeMillis() - timeout) < Constants.ELEVATOR_SWITCH_TIMEOUT_MS) {
				return false;
			} else {
				return true;
			}
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}