package org.usfirst.frc.team226.robot.auton;

import org.hammerhead226.sharkmacro.actions.ActionList;
import org.hammerhead226.sharkmacro.actions.ActionListParser;
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
public class ExecuteChoiceMacro extends Command {
	
	private String leftName;
	private String rightName;
	private Profile profileToExecute;
	private ActionList actionListToExecute;

	private TalonSRX[] talons = Robot.driveTrain.getMotionProfileTalons();

    public ExecuteChoiceMacro(String leftName, String rightName) {
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
		ActionListParser alParser;
		if (left) {
			pParser = new ProfileParser(leftName);
			alParser = new ActionListParser(leftName);
			System.out.println("Executing profile... " + leftName);
			System.out.println("Executing ActionList... " + leftName);
		} else {
			pParser = new ProfileParser(rightName);
			alParser = new ActionListParser(rightName);
			System.out.println("Executing profile... " + rightName);
			System.out.println("Executing ActionList... " + rightName);
		}
		
		profileToExecute = pParser.toObject(talons[0], talons[1], Constants.DT_LEFT_PIDSLOT_IDX, Constants.DT_RIGHT_PIDSLOT_IDX);
		actionListToExecute = alParser.toObject();
		profileToExecute.execute();
		actionListToExecute.execute();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return profileToExecute.isFinished() && actionListToExecute.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	profileToExecute.onInterrupt();
    	actionListToExecute.onInterrupt();
    }
}
