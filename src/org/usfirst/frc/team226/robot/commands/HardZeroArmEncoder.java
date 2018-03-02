package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class HardZeroArmEncoder extends InstantCommand {

    public HardZeroArmEncoder() {
        super();
        // Use requires() here to declare subsystem dependencies
         requires(Robot.arm);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.arm.hardZeroEncoder();
    }

}
