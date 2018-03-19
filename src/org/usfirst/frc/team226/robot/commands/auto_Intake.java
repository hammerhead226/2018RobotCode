package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class auto_Intake extends RecordableCommand {

    public auto_Intake() {
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.intake.driveIntake(-1);
    	Robot.intake.driveRollers(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.intake.neutralOutput();
    	Robot.intake.rollersNeutral();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	Robot.intake.neutralOutput();
    	Robot.intake.rollersNeutral();
    }
}
