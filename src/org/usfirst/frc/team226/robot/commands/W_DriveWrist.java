package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class W_DriveWrist extends Command {

	public W_DriveWrist() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.wrist);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed;

		if (Robot.oi.manip.getRightJoystick_Y() == 0) {
			System.out.println(0.04);
			speed = 0.04;
		} else {
			System.out.println(Robot.oi.manip.getLeftJoystick_Y());
			speed = Robot.oi.manip.getRightJoystick_Y();
		}

		Robot.wrist.driveWrist(speed);
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
