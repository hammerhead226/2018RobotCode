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

	public void compressorOn() {
		compressor.setClosedLoopControl(true);
		compressor.start();
	}

	public void compressorOff() {
		compressor.setClosedLoopControl(false);
		compressor.stop();
	}

	public PneumaticsSystem() {
		compressor.start();
	}

	public void initDefaultCommand() {
	}

	private DoubleSolenoid leftShifter = new DoubleSolenoid(RobotMap.DT_LEFT_SHIFTER_PCM, RobotMap.DT_LEFT_SHIFTER_1,
			RobotMap.DT_LEFT_SHIFTER_2);
	private DoubleSolenoid rightShifter = new DoubleSolenoid(RobotMap.DT_RIGHT_SHIFTER_PCM, RobotMap.DT_RIGHT_SHIFTER_1,
			RobotMap.DT_RIGHT_SHIFTER_2);
	private DoubleSolenoid leftIntake = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_SHIFTER_LEFT_1,
			RobotMap.INTAKE_SHIFTER_LEFT_2);
	private DoubleSolenoid rightIntake = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_SHIFTER_RIGHT_1,
			RobotMap.INTAKE_SHIFTER_RIGHT_2);

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

	public void toggleShooter() {
		if (shooterVal == DoubleSolenoid.Value.kForward) {
			shooterVal = DoubleSolenoid.Value.kReverse;
		} else {
			shooterVal = DoubleSolenoid.Value.kForward;
		}
	}

	private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;

	public void shiftIntake() {
		if (intakeVal == DoubleSolenoid.Value.kForward) {
			intakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			intakeVal = DoubleSolenoid.Value.kForward;
		}
		leftIntake.set(intakeVal);
		rightIntake.set(intakeVal);
	}

	public void shiftIntakeNeutral() {
		rightIntake.set(DoubleSolenoid.Value.kOff);
		leftIntake.set(DoubleSolenoid.Value.kOff);
	}

}
