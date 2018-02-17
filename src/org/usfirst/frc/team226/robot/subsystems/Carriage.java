package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.CarriageControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import util.Photoeye;

/**
 *
 */
public class Carriage extends Subsystem {

	private TalonSRX right = new TalonSRX(RobotMap.CARRIAGE_RIGHT);
	private TalonSRX left = new TalonSRX(RobotMap.CARRIAGE_LEFT);
	private Photoeye photoEye = new Photoeye(RobotMap.ELEVATOR_PHOTO_EYE, Constants.ELEVATOR_PHOTO_EYE_MIN_VOLTAGE);

	public Carriage() {
		left.setInverted(Constants.CARRIAGE_INVERT_LEFT);
		right.setInverted(Constants.CARRIAGE_INVERT_RIGHT);

		right.follow(left);
	}

	public void pushOut() {
		left.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
	}

	public void pullIn() {
		left.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
	}

	public void inOrOut(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}

	public void intakeAndCarriageIn(double speed) {
		if (!photoEye.getCovered()) {
			left.set(ControlMode.PercentOutput, speed);
		} else {
			left.set(ControlMode.PercentOutput, 0);
		}
	}

	public void intakeAndCarriageOut() {
		left.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new CarriageControl());
	}
}