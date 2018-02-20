package org.usfirst.frc.team226.robot.auton;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleActionListRecording extends InstantCommand {

	public ToggleActionListRecording() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	protected void initialize() {
		Robot.driveTrain.toggleActionListRecording();
	}

}
