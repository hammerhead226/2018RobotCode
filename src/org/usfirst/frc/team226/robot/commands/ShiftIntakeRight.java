package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftIntakeRight extends InstantCommand {

    public ShiftIntakeRight() {
        super();
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.pneumaticsSystem.shiftIntakeRight();
    	System.out.println("rightworks");
    }

}
