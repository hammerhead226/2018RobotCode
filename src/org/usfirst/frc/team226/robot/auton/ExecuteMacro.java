package org.usfirst.frc.team226.robot.auton;

import org.hammerhead226.sharkmacro.actions.ActionList;
import org.hammerhead226.sharkmacro.actions.ActionListParser;
import org.hammerhead226.sharkmacro.motionprofiles.Profile;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExecuteMacro extends Command {
	
	private Profile profileToExecute;
	private ActionList actionListToExecute;
	private boolean hasName = false;
	private String name;
	private String alName;

	private TalonSRX[] talons = Robot.driveTrain.getMotionProfileTalons();

    public ExecuteMacro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
    
    public ExecuteMacro(String name) {
    	requires(Robot.driveTrain);
    	this.name = name;
    	this.alName = name;
    	this.hasName = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ProfileParser pParser;
		ActionListParser alParser;
		
		name = (name == null) ? ProfileParser.getNewestFilename() : name;
		alName = (alName == null) ? ActionListParser.getNewestFilename() : alName;

		
    	pParser = new ProfileParser(name);
		alParser = new ActionListParser(alName);
		System.out.println("Executing profile... " + name);
		System.out.println("Executing ActionList... " + alName);
		
		profileToExecute = pParser.toObject(talons[0], talons[1], Constants.DT_LEFT_PIDSLOT_IDX, Constants.DT_RIGHT_PIDSLOT_IDX);
		actionListToExecute = alParser.toObject();
		profileToExecute.execute();
		actionListToExecute.execute();
		
		name = hasName ? name : null;
		alName = hasName ? alName : null;
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
