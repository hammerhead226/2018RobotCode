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
	private DoubleSolenoid intake = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_SHIFTER_1,
			RobotMap.INTAKE_SHIFTER_2);
	private DoubleSolenoid shooterPancake = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.SHOOTER_PANCAKE_1,
			RobotMap.SHOOTER_PANCAKE_2);
	private DoubleSolenoid shooter = new DoubleSolenoid(RobotMap.ARM_PCM, RobotMap.SHOOTER_PISTON_1,
			RobotMap.SHOOTER_PISTON_2);
	private DoubleSolenoid intakeRoller = new DoubleSolenoid(RobotMap.ARM_PCM, RobotMap.INTAKE_ROLLER_SHIFTER_1,
			RobotMap.INTAKE_ROLLER_SHIFTER_2);

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
	
	public void shooterForward() {
		shooter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shooterReverse() {
		shooter.set(DoubleSolenoid.Value.kReverse);
	}

	public void actuateShooterNeutral() {
		shooter.set(DoubleSolenoid.Value.kOff);
	}
	
	private DoubleSolenoid.Value shooterPancakeVal = DoubleSolenoid.Value.kOff;

	public void toggleShooterPancake() {
		if (shooterPancakeVal == DoubleSolenoid.Value.kForward) {
			shooterPancakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			shooterPancakeVal = DoubleSolenoid.Value.kForward;
		}
		shooterPancake.set(shooterPancakeVal);
	}
	
	public void shooterPancakeForward() {
		shooterPancake.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shooterPancakeReverse() {
		shooterPancake.set(DoubleSolenoid.Value.kReverse);
	}

	public void actuateShooterPancakeNeutral() {
		shooterPancake.set(DoubleSolenoid.Value.kOff);
	}

	private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;

	public void shiftIntake() {
		if (intakeVal == DoubleSolenoid.Value.kForward) {
			intakeVal = DoubleSolenoid.Value.kReverse;
		} else {
			intakeVal = DoubleSolenoid.Value.kForward;
		}
		intake.set(intakeVal);
	}

	public void shiftIntakeNeutral() {
		intake.set(DoubleSolenoid.Value.kOff);
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
