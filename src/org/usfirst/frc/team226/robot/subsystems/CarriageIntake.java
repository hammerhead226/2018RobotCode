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

	private Photoeye photoEye = new Photoeye(RobotMap.ELEVATOR_PHOTO_EYE,Constants.ELEVATOR_PHOTO_EYE_THRESHOLD);

	public CarriageIntake() {
		leftCarriage.setInverted(Constants.CARRIAGE_INVERT_LEFT);
		rightCarriage.setInverted(Constants.CARRIAGE_INVERT_RIGHT);

		rightCarriage.follow(leftCarriage);
		rightIntake.follow(leftIntake);

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

	public void driveCarriage(double speed) {
		leftCarriage.set(ControlMode.PercentOutput, speed);
	}

	/*
	 * public boolean photoEyeOpen() { return !photoEye.getCovered(); }
	 */

	public void initDefaultCommand() {
		setDefaultCommand(new GrpCarriageIntakeDualControl());
	}

	public void driveIntake() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_INTAKE_SPEED);
	}

	public void driveIntake(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
	}

	public void outtake() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
	}

	public void intakeNeutral() {
		leftIntake.neutralOutput();
	}

	public void carriageNeutral() {
		leftCarriage.neutralOutput();
	}

	public void unJamClockwise() {
		rightIntake.setInverted(!Constants.INTAKE_INVERT_RIGHT);
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_UN_JAM_SPEED);
	}

	public void unJamCounterClockwise() {
		rightIntake.setInverted(!Constants.INTAKE_INVERT_RIGHT);
		leftIntake.set(ControlMode.PercentOutput, -Constants.INTAKE_UN_JAM_SPEED);
	}

	public void unInvert() {
		rightIntake.setInverted(Constants.INTAKE_INVERT_RIGHT);
		leftIntake.setInverted(Constants.INTAKE_INVERT_LEFT);
	}
	
	public boolean isPhotoeyeOpen() {
		return !photoEye.getCovered();
	}

	public void runCarriageAndIntake(double height) {
		
		//Checks if elevator height is suitable for carriage + intake or just carriage
		if (height < Constants.ELEVATOR_INTAKE_TOLERANCE) {
			//In pull in  mode
			if(Robot.oi.driver.getTriggers() <  0) {
				//Photoeye
				if(isPhotoeyeOpen()) {
					System.out.println("intake");
					pullInCarriage();
					driveIntake(Robot.oi.driver.getRightTrigger());
				} else {
					System.out.println("neutral");
					carriageNeutral();
					intakeNeutral();
				}
			} else if(Robot.oi.driver.getTriggers() == 0){
				System.out.println("neutral");
				carriageNeutral();
				intakeNeutral();
			} else {
				System.out.println("outtake");
				outtake();
				pushOutCarriage();	
			}
			
		} else {
			System.out.println("manip carriage");
			driveCarriage(Robot.oi.driver.getLeftJoystick_Y());
			System.out.println(Robot.oi.driver.getLeftJoystick_Y())
			;
		}
	}

}
