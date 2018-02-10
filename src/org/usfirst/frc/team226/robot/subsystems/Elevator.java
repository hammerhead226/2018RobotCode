package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

// aUTh0R = vAruN rAj35h
public class Elevator extends Subsystem {

	TalonSRX frontLeft = new TalonSRX(RobotMap.EL_FRONT_LEFT);
	TalonSRX frontRight = new TalonSRX(RobotMap.EL_FRONT_RIGHT);
	TalonSRX rearLeft = new TalonSRX(RobotMap.EL_REAR_LEFT);
	TalonSRX rearRight = new TalonSRX(RobotMap.EL_REAR_RIGHT);

	DigitalInput hallEffect = new DigitalInput(RobotMap.HALL_EFFECT_SENSOR);


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Elevator() {
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

		frontLeft.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, 0);
		frontRight.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, 0);
		rearLeft.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, 0);
		rearRight.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, 0);

		frontLeft.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
		frontRight.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
		rearLeft.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
		rearRight.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);

		rearLeft.setInverted(true);
		rearRight.setInverted(true);

		frontRight.follow(frontLeft);
		rearLeft.follow(frontLeft);
		rearRight.follow(frontLeft);
	}

	public void setElevator(int height) {
		switch (height) {
		case 0:
			frontLeft.set(ControlMode.MotionMagic, Constants.INTAKE_HEIGHT);
			break;
		case 1:
			frontLeft.set(ControlMode.MotionMagic, Constants.SWITCH_HEIGHT);
			break;
		case 2:
			frontLeft.set(ControlMode.MotionMagic, Constants.POW_HEIGHT);
			break;
		case 3:
			frontLeft.set(ControlMode.MotionMagic, Constants.SCALE_HEIGHT);
			break;
		}

	}

	public void zero() {
		if (hallEffect.get()) {
			frontLeft.setSelectedSensorPosition(0, 0, 0);
		}
	}
}
