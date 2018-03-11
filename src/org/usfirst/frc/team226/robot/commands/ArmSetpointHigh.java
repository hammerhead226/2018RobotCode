package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.subsystems.Arm;

/**
 *
 */
public class ArmSetpointHigh extends RecordableCommand {

    public ArmSetpointHigh() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.arm);
    }

    // Called once when the command executes
    protected void initialize() {
    	super.initialize();
    	Robot.arm.setArmSetpoint(Arm.ArmSetpoint.STRAIGHT_UP);
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
