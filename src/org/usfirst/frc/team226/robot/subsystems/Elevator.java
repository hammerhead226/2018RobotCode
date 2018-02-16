package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Elevator extends Subsystem {

	TalonSRX frontLeft = new TalonSRX(RobotMap.ELEVATOR_FRONT_LEFT);
	TalonSRX frontRight = new TalonSRX(RobotMap.ELEVATOR_FRONT_RIGHT);
	TalonSRX rearLeft = new TalonSRX(RobotMap.ELEVATOR_REAR_LEFT);
	TalonSRX rearRight = new TalonSRX(RobotMap.ELEVATOR_REAR_RIGHT);

	DigitalInput hallEffect = new DigitalInput(RobotMap.ELEVATOR_HALL_EFFECT_SENSOR);

	public int height = 0;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		
	}

	public Elevator() {

		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

		frontLeft.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		frontRight.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		rearLeft.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		rearRight.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);

		frontLeft.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		frontRight.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		rearLeft.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		rearRight.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		rearLeft.setInverted(Constants.ELEVATOR_INVERT_RL);
		frontLeft.setInverted(Constants.ELEVATOR_INVERT_FL);
		rearRight.setInverted(Constants.ELEVATOR_INVERT_RR);
		frontRight.setInverted(Constants.ELEVATOR_INVERT_FR);

		frontRight.follow(frontLeft);
		rearLeft.follow(frontLeft);
		rearRight.follow(frontLeft);
	}

	public void moveElevatorUp() {
		if (height == 3) {
			height = 3;
		} else {
			height++;
		}
		
		setElevator();
		
		zero();
	}

	public void moveElevatorDown() {
		if (height == 0) {
			height = 0;
		} else {
			height--;
		}
		
		setElevator();
		
		zero();
		
	}

	public void setElevator() {

		switch (height) {
		case 0:
			frontLeft.set(ControlMode.MotionMagic, Constants.ELEVATOR_INTAKE_HEIGHT);
			break;
		case 1:
			frontLeft.set(ControlMode.MotionMagic, Constants.ELEVATOR_SWITCH_HEIGHT);
			break;
		case 2:
			frontLeft.set(ControlMode.MotionMagic, Constants.ELEVATOR_POW_HEIGHT);
			break;
		case 3:
			frontLeft.set(ControlMode.MotionMagic, Constants.ELEVATOR_SCALE_HEIGHT);
			break;
		}

	}

	public void zero() {
		if (hallEffect.get()) {
			frontLeft.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
		}
	}
	
	public void hardZero() {
		frontLeft.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
	}
}
