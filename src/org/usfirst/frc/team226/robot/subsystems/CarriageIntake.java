package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.CarriageIntakeControl;

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

		leftIntake.setInverted(Constants.INTAKE_INVERT_LEFT);
		rightIntake.setInverted(Constants.INTAKE_INVERT_RIGHT);

		leftIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT_MS);
		rightIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT_MS);
		leftIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		rightIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
		
		leftCarriage.configContinuousCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT, Constants.CARRIAGE_TIMEOUT_MS);
		rightCarriage.configContinuousCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT, Constants.CARRIAGE_TIMEOUT_MS);
		leftCarriage.enableCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT_ENABLED);
		rightCarriage.enableCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT_ENABLED);
		
		leftIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT_MS);
		rightIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT_MS);
		leftIntake.enableCurrentLimit(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
		rightIntake.enableCurrentLimit(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
		
		leftCarriage.configVoltageCompSaturation(Constants.CARRIAGE_VOLTAGE_LIMIT, Constants.CARRIAGE_TIMEOUT_MS);
		rightCarriage.configVoltageCompSaturation(Constants.CARRIAGE_VOLTAGE_LIMIT, Constants.CARRIAGE_TIMEOUT_MS);
		leftCarriage.enableCurrentLimit(Constants.CARRIAGE_VOLTAGE_LIMIT_ENABLED);
		rightCarriage.enableCurrentLimit(Constants.CARRIAGE_VOLTAGE_LIMIT_ENABLED);
		
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

	public void initDefaultCommand() {
		setDefaultCommand(new CarriageIntakeControl());
	}

	public void driveIntake() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_INTAKE_SPEED);
		rightIntake.set(ControlMode.PercentOutput, Constants.INTAKE_INTAKE_SPEED);
	}

	public void driveIntake(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, speed);
	}

	public void driveIntakeReverse() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
		rightIntake.set(ControlMode.PercentOutput , Constants.INTAKE_INTAKE_SPEED);
	}
	
	public void driveIntakeReverse(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, speed);
	}

	public void intakeNeutral() {
		leftIntake.neutralOutput();
		rightIntake.neutralOutput();
	}

	public void carriageNeutral() {
		leftCarriage.neutralOutput();
	}

	public void unJamClockwise() {
		leftIntake.set(ControlMode.PercentOutput, Constants.INTAKE_UN_JAM_SPEED);
		rightIntake.set(ControlMode.PercentOutput, -Constants.INTAKE_UN_JAM_SPEED);
	}

	public void unJamCounterClockwise() {
		leftIntake.set(ControlMode.PercentOutput, -Constants.INTAKE_UN_JAM_SPEED);
		rightIntake.set(ControlMode.PercentOutput, Constants.INTAKE_UN_JAM_SPEED);
	}

	public void runCarriageAndIntake() {
		if (Robot.elevatorHeight < Constants.ELEVATOR_INTAKE_TOLERANCE) {
			if(Robot.oi.driver.getTriggers() <  0) {
				if(!photoEye.getCovered()) {
					pullInCarriage();
					driveIntake(Robot.oi.driver.getRightTrigger());
				} else { 
					carriageNeutral();
					intakeNeutral();
				}
			} else if(Robot.oi.driver.getTriggers() == 0){
				carriageNeutral();
				intakeNeutral();
			} else {
				driveIntakeReverse();
				pushOutCarriage();	
			}
			
		} else {
			driveCarriage(Robot.oi.driver.getLeftJoystick_Y());
		}
	}

}
