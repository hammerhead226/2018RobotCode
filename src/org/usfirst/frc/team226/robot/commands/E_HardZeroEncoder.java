package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class E_HardZeroEncoder extends InstantCommand {

    public E_HardZeroEncoder() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.elevator.hardZeroEncoder();
    }

}
