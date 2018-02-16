package org.usfirst.frc.team226.robot.subsystems;

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
	
	private DoubleSolenoid leftFlipIntake = new DoubleSolenoid(RobotMap.INTAKE_LEFT_PCM, RobotMap.INTAKE_LEFT_FLIP_1, RobotMap.INTAKE_LEFT_FLIP_2);
	private DoubleSolenoid rightFlipIntake = new DoubleSolenoid(RobotMap.INTAKE_RIGHT_PCM, RobotMap.INTAKE_RIGHT_FLIP_1, RobotMap.INTAKE_RIGHT_FLIP_2);
	private DoubleSolenoid leftOpenIntake = new DoubleSolenoid(RobotMap.INTAKE_LEFT_PCM, RobotMap.INTAKE_LEFT_FLIP_1, RobotMap.INTAKE_LEFT_FLIP_2);
	private DoubleSolenoid rightOpenIntake = new DoubleSolenoid(RobotMap.INTAKE_RIGHT_PCM, RobotMap.INTAKE_RIGHT_FLIP_1, RobotMap.INTAKE_RIGHT_FLIP_2);


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
	
	private DoubleSolenoid.Value flipVal = DoubleSolenoid.Value.kOff;
	
	public void flipIntake() {
		if(flipVal == DoubleSolenoid.Value.kForward) {
			flipVal = DoubleSolenoid.Value.kReverse;
		} else {
			flipVal = DoubleSolenoid.Value.kForward;
		}
		leftFlipIntake.set(flipVal);
		rightFlipIntake.set(flipVal);
	}
	
	public void flipIntakeNeutral() {
		leftFlipIntake.set(DoubleSolenoid.Value.kOff);
		rightFlipIntake.set(DoubleSolenoid.Value.kOff);
	}
	
	private DoubleSolenoid.Value openVal = DoubleSolenoid.Value.kOff;
	
	public void openIntake() {
		if(openVal == DoubleSolenoid.Value.kForward) {
			openVal = DoubleSolenoid.Value.kReverse;
		} else {
			openVal = DoubleSolenoid.Value.kForward;
		}
		leftOpenIntake.set(openVal);
		rightOpenIntake.set(openVal);
	}
	
	public void openIntakeNeutral() {
		leftOpenIntake.set(DoubleSolenoid.Value.kOff);
		rightOpenIntake.set(DoubleSolenoid.Value.kOff);
	}
}