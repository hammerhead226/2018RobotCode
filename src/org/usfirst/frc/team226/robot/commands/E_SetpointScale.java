package org.usfirst.frc.team226.robot.commands;

import org.hammerhead226.sharkmacro.actions.RecordableCommand;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.subsystems.Elevator;

/**
 *
 */
public class E_SetpointScale extends RecordableCommand {

    public E_SetpointScale() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.elevator.setElevatorSetpoint(Elevator.ElevatorSetpoint.SCALE);
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
