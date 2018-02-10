package org.usfirst.frc.team226.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSystem extends Subsystem {

	public Compressor compressor = new Compressor(12);

	public PneumaticsSystem() {
		compressor.start();
	}

	public void initDefaultCommand() {
	}

	private DoubleSolenoid leftShifter = new DoubleSolenoid(12, 4, 5);
	private DoubleSolenoid rightShifter = new DoubleSolenoid(12, 6, 7);

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
}
