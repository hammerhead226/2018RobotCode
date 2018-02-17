package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorPID extends Command {

	public ElevatorPID() {
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Math.abs(Robot.m_oi.manip.getLeftJoystick_Y()) > 0) {
			Robot.elevator.fineMovement(Robot.m_oi.manip.getLeftJoystick_Y());
		} else {
			if (Robot.m_oi.manip.getAButtonPressed()) {
				Robot.elevator.setElevator(Constants.ELEVATOR_INTAKE_HEIGHT);
			} else if (Robot.m_oi.manip.getBButtonPressed()) {
				Robot.elevator.setElevator(Constants.ELEVATOR_SWITCH_HEIGHT);
			} else if (Robot.m_oi.manip.getXButtonPressed()) {
				Robot.elevator.setElevator(Constants.ELEVATOR_POW_HEIGHT);
			} else if (Robot.m_oi.manip.getyButtonPressed()) {
				Robot.elevator.setElevator(Constants.ELEVATOR_SCALE_HEIGHT);
			} else {
				Robot.elevator.setElevator(Robot.elevator.getElevatorSetpoint());
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
