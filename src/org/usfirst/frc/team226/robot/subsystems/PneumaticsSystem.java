package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

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

	private DoubleSolenoid leftShifter = new DoubleSolenoid(RobotMap.DT_LEFT_SHIFTER_PCM, RobotMap.DT_LEFT_SHIFTER_1,
			RobotMap.DT_LEFT_SHIFTER_2);
	private DoubleSolenoid rightShifter = new DoubleSolenoid(RobotMap.DT_RIGHT_SHIFTER_PCM, RobotMap.DT_RIGHT_SHIFTER_1,
			RobotMap.DT_RIGHT_SHIFTER_2);
	private DoubleSolenoid leftIntake = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_LEFT_SHIFTER_1,
			RobotMap.INTAKE_LEFT_SHIFTER_2);
	private DoubleSolenoid intakeRight = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_RIGHT_SHIFTER_1,
			RobotMap.INTAKE_RIGHT_SHIFTER_2);
	private DoubleSolenoid shooter = new DoubleSolenoid(RobotMap.ARM_PCM, RobotMap.SHOOTER_PISTON_1,
			RobotMap.SHOOTER_PISTON_2);
	private DoubleSolenoid intakeRoller = new DoubleSolenoid(RobotMap.ARM_PCM,
			RobotMap.INTAKE_ROLLER_SHIFTER_1, RobotMap.INTAKE_ROLLER_SHIFTER_2);
	
	private DoubleSolenoid.Value leftShifterVal = DoubleSolenoid.Value.kOff;

	public void shiftDriveTrainLeft() {
		if (leftShifterVal == DoubleSolenoid.Value.kForward) {
			leftShifterVal = DoubleSolenoid.Value.kReverse;
		} else {
			leftShifterVal = DoubleSolenoid.Value.kForward;
		}
		leftShifter.set(leftShifterVal);
	}

	private DoubleSolenoid.Value rightShifterVal = DoubleSolenoid.Value.kOff;

	public void shiftDriveTrainRight() {
		if (rightShifterVal == DoubleSolenoid.Value.kForward) {
			rightShifterVal = DoubleSolenoid.Value.kReverse;
		} else {
			rightShifterVal = DoubleSolenoid.Value.kForward;
		}
		rightShifter.set(rightShifterVal);
	}

	public void shiftDriveTrain() {
		shiftDriveTrainLeft();
		shiftDriveTrainRight();
	}

	public void shiftDriveTrainForward() {
		rightShifter.set(DoubleSolenoid.Value.kForward);
		leftShifter.set(DoubleSolenoid.Value.kForward);
	}

	public void shiftDriveTrainReverse() {
		rightShifter.set(DoubleSolenoid.Value.kReverse);
		leftShifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftDriveTrainNeutral() {
		leftShifter.set(DoubleSolenoid.Value.kOff);
		rightShifter.set(DoubleSolenoid.Value.kOff);
	}

	private DoubleSolenoid.Value shooterVal = DoubleSolenoid.Value.kOff;

	public void actuateShooter() {
		if (shooterVal == DoubleSolenoid.Value.kForward) {
			shooterVal = DoubleSolenoid.Value.kReverse;
		} else {
			shooterVal = DoubleSolenoid.Value.kForward;
		}
		shooter.set(shooterVal);
	}

	public void actuateShooterNeutral() {
		shooter.set(DoubleSolenoid.Value.kOff);
	}

	private DoubleSolenoid.Value leftIntakeVal = DoubleSolenoid.Value.kForward;

	public void shiftIntakeLeft() {
		if (leftIntakeVal == DoubleSolenoid.Value.kForward) {
			leftIntakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			leftIntakeVal = DoubleSolenoid.Value.kForward;
		}
		leftIntake.set(leftIntakeVal);
	}

	private DoubleSolenoid.Value rightIntakeVal = DoubleSolenoid.Value.kForward;

	public void shiftIntakeRight() {
		if (rightIntakeVal == DoubleSolenoid.Value.kForward) {
			rightIntakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			rightIntakeVal = DoubleSolenoid.Value.kForward;
		}
		intakeRight.set(rightIntakeVal);
	}

	public void shiftIntake() {
		shiftIntakeLeft();
		shiftIntakeRight();
	}

	public void shiftIntakeNeutral() {
		leftIntake.set(DoubleSolenoid.Value.kOff);
		intakeRight.set(DoubleSolenoid.Value.kOff);
	}

	private DoubleSolenoid.Value intakeRollerVal = DoubleSolenoid.Value.kForward;

	public void shiftIntakeRollers() {
		if (intakeRollerVal == DoubleSolenoid.Value.kForward) {
			intakeRollerVal = DoubleSolenoid.Value.kReverse;
		} else {
			intakeRollerVal = DoubleSolenoid.Value.kForward;
		}
		intakeRoller.set(intakeRollerVal);
	}
	
	public void shiftIntakeRollerNeutral() {
		intakeRoller.set(DoubleSolenoid.Value.kOff);
	}
}
