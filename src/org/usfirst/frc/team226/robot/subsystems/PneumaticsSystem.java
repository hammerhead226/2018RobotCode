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
	private DoubleSolenoid intakeLeft = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_LEFT_1, RobotMap.INTAKE_LEFT_2);
	private DoubleSolenoid intakeRight = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_RIGHT_1, RobotMap.INTAKE_RIGHT_2);
	private DoubleSolenoid shooter = new DoubleSolenoid(RobotMap.ARM_PCM, RobotMap.SHOOTER_1, RobotMap.SHOOTER_2);


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
	
	DoubleSolenoid.Value shooterVal = DoubleSolenoid.Value.kOff;
	public void actuateShooter() {
		if(shooterVal == DoubleSolenoid.Value.kForward) {
			shooterVal = DoubleSolenoid.Value.kReverse;
		} else {
			shooterVal = DoubleSolenoid.Value.kForward;
		}
		shooter.set(shooterVal);
	}
	
	public void actuateShooterNeutral() {
		shooter.set(DoubleSolenoid.Value.kOff);
	}
	
	DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kOff;
	public void intake() {
		if(intakeVal == DoubleSolenoid.Value.kForward) {
			intakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			intakeVal = DoubleSolenoid.Value.kForward;
		}
		intakeLeft.set(intakeVal);
		intakeRight.set(intakeVal);
	}
	
	public void intakeNeutral() {
		intakeLeft.set(DoubleSolenoid.Value.kOff);
		intakeRight.set(DoubleSolenoid.Value.kOff);
	}

}
