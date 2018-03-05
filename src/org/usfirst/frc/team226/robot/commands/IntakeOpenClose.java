package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakeOpenClose extends InstantCommand {

    public IntakeOpenClose() {
        super();
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.pneumaticsSystem.shiftIntakeLeft();
    	Robot.pneumaticsSystem.intake();
    }

}
