package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;

/**
 *
 */
public class PS_ShiftIntake extends RecordableCommand {

    public PS_ShiftIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	super.initialize();
    	Robot.pneumaticsSystem.shiftIntake();
    	Robot.pneumaticsSystem.shiftIntakeRollers();
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
