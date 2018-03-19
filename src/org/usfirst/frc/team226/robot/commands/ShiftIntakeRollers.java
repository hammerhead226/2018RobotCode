package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftIntakeRollers extends InstantCommand {

    public ShiftIntakeRollers() {
        super();
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	Robot.pneumaticsSystem.shiftIntakeRollers();
    }

}