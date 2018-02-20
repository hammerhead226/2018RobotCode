package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrpCarriageIntakeDualControl extends CommandGroup {

    public GrpCarriageIntakeDualControl() {
    	requires(Robot.carriageIntake);
    	requires(Robot.elevator);
    	
    	addSequential(new CarriageIntakeControl(Robot.elevator.getElevatorPosition()));
    }
}
