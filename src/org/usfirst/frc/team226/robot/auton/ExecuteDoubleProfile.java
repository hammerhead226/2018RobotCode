package org.usfirst.frc.team226.robot.auton;

import org.hammerhead226.sharkmacro.motionprofiles.Profile;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExecuteDoubleProfile extends Command {

	private String leftName;
	private String rightName;
	private Profile profileToExecute;

	private TalonSRX[] talons = Robot.driveTrain.getMotionProfileTalons();

	public ExecuteDoubleProfile(String leftName, String rightName) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		this.leftName = leftName;
		this.rightName = rightName;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		boolean left = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L';
		ProfileParser pParser;
		if (left) {
			pParser = new ProfileParser(leftName);
			System.out.println("Executing profile... " + leftName);
		} else {
			pParser = new ProfileParser(rightName);
			System.out.println("Executing profile... " + rightName);
		}
		profileToExecute = pParser.toObject(talons[0], talons[1]);
		
		profileToExecute.execute(Constants.DT_LEFT_PIDSLOT_IDX, Constants.DT_RIGHT_PIDSLOT_IDX);
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
