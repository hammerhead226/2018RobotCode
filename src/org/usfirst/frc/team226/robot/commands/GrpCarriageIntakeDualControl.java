package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrpCarriageIntakeDualControl extends CommandGroup {

    public GrpCarriageIntakeDualControl() {
    	requires(Robot.elevator);
    	requires(Robot.carriageIntake);
    	
    	addParallel(new CarriageIntakeControl(Robot.elevator.getElevatorPosition()));
    }
}
