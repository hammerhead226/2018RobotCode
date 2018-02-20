package org.usfirst.frc.team226.robot.auton;

import org.hammerhead226.sharkmacro.actions.ActionList;
import org.hammerhead226.sharkmacro.actions.ActionListParser;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExecuteSavedActionList extends Command {

	private String name;
	private ActionList actionListToExecute;

	public ExecuteSavedActionList(String name) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.name = name;
	}

	public ExecuteSavedActionList() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ActionListParser alParser;
		name = (name == null) ? ActionListParser.getNewestFilename() : name;
		alParser = new ActionListParser(name);
		actionListToExecute = alParser.toObject();
		actionListToExecute.execute();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return actionListToExecute.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
