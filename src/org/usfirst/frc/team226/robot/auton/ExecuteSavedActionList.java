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
	private ActionList al;
	private boolean noName = false;

	public ExecuteSavedActionList(String name) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.name = name;
	}

	public ExecuteSavedActionList() {
		noName = true;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ActionListParser alParser;
		name = noName ? ActionListParser.getNewestFilename() : name;
		alParser = new ActionListParser(name);
		al = alParser.toObject();
		al.execute();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return al.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
