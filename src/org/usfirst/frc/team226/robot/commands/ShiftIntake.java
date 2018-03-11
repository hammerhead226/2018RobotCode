package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class ShiftIntake extends RecordableCommand {

    public ShiftIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	super.initialize();
    	Robot.pneumaticsSystem.shiftIntake();
    }
    
    protected void execute() {
    }
    
    protected boolean isFinished() {
    	return true;
    }
    
    protected void end() {
    	super.end();
    }
    
    protected void interrupted() {
    	super.interrupted();
    }


}
