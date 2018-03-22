package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class PS_CompressorOn extends InstantCommand {

    public PS_CompressorOn() {
        super();
        // Use requires() here to declare subsystem dependencies
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.pneumaticsSystem.compressorOn();
    }

}
