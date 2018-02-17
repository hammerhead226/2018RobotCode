package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ElevatorManualMovement;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Elevator extends Subsystem {

	TalonSRX left = new TalonSRX(RobotMap.ELEVATOR_FRONT_LEFT);
	TalonSRX right = new TalonSRX(RobotMap.ELEVATOR_FRONT_RIGHT);

	DigitalInput hallEffect = new DigitalInput(RobotMap.ELEVATOR_HALL_EFFECT_SENSOR);

	enum ElevatorHeight {
		INTAKE, SWITCH, POWER, SCALE
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorManualMovement());
	}

	public Elevator() {

		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
		
		left.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
		right.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
		
		left.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);	
		right.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);			
		
		left.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		right.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);

		left.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.ELEVATOR_INVERT_L);
		right.setInverted(Constants.ELEVATOR_INVERT_R);

		right.follow(left);
	}

	public void moveElevatorToIntake() {
		setElevator(ElevatorHeight.INTAKE);
	}

	public void moveElevatorToPower() {
		setElevator(ElevatorHeight.POWER);
	}

	public void moveElevatorToSwitch() {
		setElevator(ElevatorHeight.SWITCH);
	}

	public void moveElevatorToScale() {
		setElevator(ElevatorHeight.SCALE);
	}

	public void setElevator(ElevatorHeight height) {
		switch (height) {
		case INTAKE:
			left.set(ControlMode.MotionMagic, Constants.ELEVATOR_INTAKE_HEIGHT);
			break;
		case SWITCH:
			left.set(ControlMode.MotionMagic, Constants.ELEVATOR_SWITCH_HEIGHT);
			break;
		case POWER:
			left.set(ControlMode.MotionMagic, Constants.ELEVATOR_POW_HEIGHT);
			break;
		case SCALE:
			left.set(ControlMode.MotionMagic, Constants.ELEVATOR_SCALE_HEIGHT);
			break;
		}

	}

	public void zeroEncoder() {
		if (hallEffect.get()) {
			left.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
		}
	}

	public void hardZeroEncoder() {
		left.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
	}

	public void fineMovement() {
		left.set(ControlMode.PercentOutput, Constants.ELEVATOR_FINE_TUNE * Robot.oi.manip.getLeftJoystick_Y());
	}
	
	public int getElevatorError() {
		return left.getClosedLoopError(Constants.ELEVATOR_PID_IDX);
	}
}
