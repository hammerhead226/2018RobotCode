package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class HardZeroElevator extends InstantCommand {

    public HardZeroElevator() {
        super();
        requires(Robot.elevator);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.elevator.hardZeroEncoder();
    	Robot.elevator.setElevator(0);
    }

}
