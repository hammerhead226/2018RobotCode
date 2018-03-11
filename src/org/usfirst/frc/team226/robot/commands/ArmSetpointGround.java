package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.subsystems.Arm;

/**
 *
 */
public class ArmSetpointGround extends RecordableCommand {

    public ArmSetpointGround() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.arm);
    }

    // Called once when the command executes
    protected void initialize() {
    	super.initialize();
    	if (Robot.arm.getArmSetpointModifierButton()) {
    		Robot.arm.setArmSetpoint(Arm.ArmSetpoint.FRONT_GROUND);
    	} else {
    		Robot.arm.setArmSetpoint(Arm.ArmSetpoint.BACK_GROUND);
    	}
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
