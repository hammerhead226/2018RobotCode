package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakeShiftNeutral extends InstantCommand {

    public IntakeShiftNeutral() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.pneumaticsSystem.flipIntakeNeutral();
    }

}
