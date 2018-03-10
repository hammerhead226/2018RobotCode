package org.usfirst.frc.team226.robot.auton;

import org.hammerhead226.sharkmacro.motionprofiles.Profile;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExecuteSavedProfile extends Command {

	private String name;
	private Profile profileToExecute;
	private boolean hasName = false;
	
	private TalonSRX[] talons = Robot.driveTrain.getMotionProfileTalons();

	public ExecuteSavedProfile(String name) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		this.name = name;
		hasName = true;
	}

	public ExecuteSavedProfile() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ProfileParser pParser;
		name = (name == null) ? ProfileParser.getNewestFilename() : name;
		pParser = new ProfileParser(name);
		System.out.println("Executing profile... " + name);
		profileToExecute = pParser.toObject(talons[0], talons[1]);
		profileToExecute.execute(Constants.DT_LEFT_PIDSLOT_IDX, Constants.DT_RIGHT_PIDSLOT_IDX);
		name = hasName ? name : null;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return profileToExecute.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		profileToExecute.onInterrupt();
	}
}
