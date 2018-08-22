package org.usfirst.frc.team226.robot.auton;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BaselineCross extends Command {
	
	private long startTime;
	private long currentTime;

    public BaselineCross() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.tankDrive(0.5, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (currentTime - startTime) > Constants.AUTONOMOUS_BASELINE_CROSS_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.tankDrive(0, 0);
    }
}
