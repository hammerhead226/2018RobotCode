package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.GrpCarriageIntakeDualControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import util.Photoeye;

/**
 *
 */
public class CarriageIntake extends Subsystem {

	private TalonSRX leftCarriage = new TalonSRX(RobotMap.CARRIAGE_LEFT);
	private TalonSRX rightCarriage = new TalonSRX(RobotMap.CARRIAGE_RIGHT);

	private TalonSRX leftIntake = new TalonSRX(RobotMap.INTAKE_LEFT);
	private TalonSRX rightIntake = new TalonSRX(RobotMap.INTAKE_RIGHT);

	private Photoeye photoEye = new Photoeye(RobotMap.ELEVATOR_PHOTO_EYE, Constants.ELEVATOR_PHOTO_EYE_THRESHOLD);

	public CarriageIntake() {
		leftCarriage.setInverted(Constants.CARRIAGE_INVERT_LEFT);
		rightCarriage.setInverted(Constants.CARRIAGE_INVERT_RIGHT);

		rightCarriage.follow(leftCarriage);
		
		leftIntake.setInverted(Constants.INTAKE_INVERT_LEFT);
		rightIntake.setInverted(Constants.INTAKE_INVERT_RIGHT);

		leftIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);
		rightIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);
		leftIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		rightIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
	}

	public void pushOutCarriage() {
		leftCarriage.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
	}

	public void pullInCarriage() {
		leftCarriage.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
	}

	public void setCarriageSpeed(double speed) {
		leftCarriage.set(ControlMode.PercentOutput, speed);
	}

	public boolean photoEyeOpen() {
		if (photoEye.getCovered()) {
			return false;
		} else {
			return true;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new GrpCarriageIntakeDualControl());
	}

	public void intake() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_INTAKE_SPEED);
		rightIntake.set(ControlMode.PercentOutput, Constants.INTAKE_INTAKE_SPEED);
	}

	public void intake(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, speed);
	}

	public void intake(double left, double right) {
		this.leftIntake.set(ControlMode.PercentOutput, left);
		this.rightIntake.set(ControlMode.PercentOutput, right);
	}

	public void outtake() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
		rightIntake.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
	}

	public void intakeNeutral() {
		leftIntake.set(ControlMode.PercentOutput, 0);
		rightIntake.set(ControlMode.PercentOutput, 0);
	}

	public void carriageNeutral() {
		leftCarriage.set(ControlMode.PercentOutput, 0);
	}

	public void runCarriageAndIntake(double height) {
		if (height < Constants.ELEVATOR_INTAKE_TOLERANCE) {
			if (Robot.oi.driver.getAButtonPressed()) {
				pushOutCarriage();
				outtake();
			} else {
				if (photoEyeOpen()) {
					pullInCarriage();
					intake(Robot.oi.driver.getTriggers());
				} else {
					intakeNeutral();
					carriageNeutral();
				}
			}
		} else {
			setCarriageSpeed(Robot.oi.manip.getLeftJoystick_Y());
		}
	}

}
