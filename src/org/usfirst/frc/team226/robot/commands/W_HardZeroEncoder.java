package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class W_HardZeroEncoder extends InstantCommand {

    public W_HardZeroEncoder() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wrist);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.wrist.hardZeroEncoder();
    }

}
