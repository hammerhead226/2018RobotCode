package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class W_WristDown extends RecordableCommand {

    public W_WristDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.wrist.driveWrist(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.wrist.neutralOutput();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	Robot.wrist.neutralOutput();
    }
}
