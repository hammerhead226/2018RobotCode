package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSystem extends Subsystem {

	public Compressor compressor = new Compressor(RobotMap.COMPRESSOR_PORT);

	public PneumaticsSystem() {
		compressor.start();
	}

	public void initDefaultCommand() {
	}

	private DoubleSolenoid leftShifter = new DoubleSolenoid(RobotMap.DT_LEFT_SHIFTER_PCM, RobotMap.DT_LEFT_SHIFTER_1, RobotMap.DT_LEFT_SHIFTER_2);
	private DoubleSolenoid rightShifter = new DoubleSolenoid(RobotMap.DT_RIGHT_SHIFTER_PCM, RobotMap.DT_RIGHT_SHIFTER_1, RobotMap.DT_RIGHT_SHIFTER_2);

	private DoubleSolenoid.Value leftVal = DoubleSolenoid.Value.kOff;

	public void shiftDriveTrainLeft() {
		if (leftVal == DoubleSolenoid.Value.kForward) {
			leftVal = DoubleSolenoid.Value.kReverse;
		} else {
			leftVal = DoubleSolenoid.Value.kForward;
		}
		leftShifter.set(leftVal);
	}

	private DoubleSolenoid.Value rightVal = DoubleSolenoid.Value.kOff;

	public void shiftDriveTrainRight() {
		if (rightVal == DoubleSolenoid.Value.kForward) {
			rightVal = DoubleSolenoid.Value.kReverse;
		} else {
			rightVal = DoubleSolenoid.Value.kForward;
		}
		rightShifter.set(rightVal);
	}

	public void shiftDriveTrain() {
		shiftDriveTrainLeft();
		shiftDriveTrainRight();
	}

	public void shiftDriveTrainNeutral() {
		leftShifter.set(DoubleSolenoid.Value.kOff);
		rightShifter.set(DoubleSolenoid.Value.kOff);
	}
	
	private DoubleSolenoid carriagePopper = new DoubleSolenoid(RobotMap.CARRIAGE_POPPER_1, RobotMap.CARRIAGE_POPPER_2);	
	public void popOut() {
		if(!Robot.isArmGround) {
			carriagePopper.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public void popIn() {
		carriagePopper.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void popNeutral() {
		carriagePopper.set(DoubleSolenoid.Value.kOff);
	}
	
	public void closePopper() {
		long t = System.currentTimeMillis();
		while((System.currentTimeMillis() - t) < Constants.ARM_CLOSE_TIME) {
			popIn();
		}
		popNeutral();
	}
}
