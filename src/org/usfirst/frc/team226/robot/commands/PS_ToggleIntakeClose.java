package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class PS_ToggleIntakeClose extends RecordableCommand {

    public PS_ToggleIntakeClose() {
        requires(Robot.pneumaticsSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.pneumaticsSystem.toggleIntakeClose();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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