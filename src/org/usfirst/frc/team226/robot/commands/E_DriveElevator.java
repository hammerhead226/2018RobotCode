package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class E_DriveElevator extends RecordableCommand {

	public E_DriveElevator() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		super.initialize();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		double speed;
		
		if(Robot.oi.manip.getLeftJoystick_Y() == 0) {
			System.out.println(0.04);
			speed = 0.04;
		} else {
			System.out.println(Robot.oi.manip.getLeftJoystick_Y());
			speed = Robot.oi.manip.getLeftJoystick_Y();
		}
		
		Robot.elevator.driveElevator(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		super.end();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		super.interrupted();
	}
}
