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

	private String name = "";
	private Profile p;
	private boolean noName = false;
	
	private TalonSRX[] talons = Robot.driveTrain.getMotionProfileTalons();

	public ExecuteSavedProfile(String name) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		this.name = name;
	}

	public ExecuteSavedProfile() {
		noName = true;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ProfileParser pParser;
		name = noName ? ProfileParser.getNewestFilename() : name;
		pParser = new ProfileParser(name);
		System.out.println("Executing profile... " + name);
		p = pParser.toObject(talons[0], talons[1]);
		p.execute(Constants.DT_LEFT_PIDSLOT_IDX, Constants.DT_RIGHT_PIDSLOT_IDX);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return p.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		p.onInterrupt();
	}
}
