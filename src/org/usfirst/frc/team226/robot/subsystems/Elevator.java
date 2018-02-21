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

	TalonSRX left1 = new TalonSRX(RobotMap.ELEVATOR_FRONT_LEFT_1);
	TalonSRX left2 = new TalonSRX(RobotMap.ELEVATOR_FRONT_LEFT_2);
	TalonSRX right1 = new TalonSRX(RobotMap.ELEVATOR_FRONT_RIGHT_1);
	TalonSRX right2 = new TalonSRX(RobotMap.ELEVATOR_FRONT_RIGHT_2);

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
		
		left1.configForwardSoftLimitEnable(Constants.ELEVATOR_ENABLE_TOP_THRESHOLD, Constants.ELEVATOR_TIMEOUT_MS);
		left1.configReverseSoftLimitEnable(Constants.ELEVATOR_ENABLE_BOTTOM_THRESHOLD, Constants.ELEVATOR_TIMEOUT_MS);
		left1.configForwardSoftLimitThreshold(Constants.ELEVATOR_TOP_THRESHOLD, Constants.ELEVATOR_TIMEOUT_MS);
		left1.configReverseSoftLimitThreshold(Constants.ELEVATOR_BOTTOM_THRESHOLD, Constants.ELEVATOR_TIMEOUT_MS);
		
		left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PID_IDX,
				Constants.ELEVATOR_TIMEOUT_MS);

		left1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);
		right1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);

		left1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);

		left1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);
		right1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);

		left1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		left1.setInverted(Constants.ELEVATOR_INVERT_L1);
		left2.setInverted(Constants.ELEVATOR_INVERT_L2);
		right1.setInverted(Constants.ELEVATOR_INVERT_R1);
		right2.setInverted(Constants.ELEVATOR_INVERT_R2);

		left2.follow(left1);
		right1.follow(left1);
		right2.follow(left1);
		
		left1.setSensorPhase(true);
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
			left1.set(ControlMode.MotionMagic, Constants.ELEVATOR_INTAKE_HEIGHT);
			break;
		case SWITCH:
			left1.set(ControlMode.MotionMagic, Constants.ELEVATOR_SWITCH_HEIGHT);
			break;
		case POWER:
			left1.set(ControlMode.MotionMagic, Constants.ELEVATOR_POW_HEIGHT);
			break;
		case SCALE:
			left1.set(ControlMode.MotionMagic, Constants.ELEVATOR_SCALE_HEIGHT);
			break;
		}

	}

	public void setElevator(double height) {
		left1.set(ControlMode.MotionMagic, height);
	}

	public void zeroEncoderGround() {
		if (hallEffect.get()) {
			left1.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
			setElevator(0);
		}
	}

	public void hardZeroEncoder() {
		left1.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
		setElevator(getElevatorPosition());
	}

	public void fineMovement(double speed) {
		left1.set(ControlMode.PercentOutput, Constants.ELEVATOR_FINE_TUNE * speed);
	}

	public int getElevatorError() {
		return left1.getClosedLoopError(Constants.ELEVATOR_PID_IDX);
	}

	public int getElevatorPosition() {
		return left1.getSelectedSensorPosition(Constants.ELEVATOR_PID_IDX);
	}

	public boolean onTarget(double setpoint) {
		if (Math.abs(getElevatorPosition() - setpoint) < Constants.ELEVATOR_ERROR_MARGIN) {
			return true;
		} else {
		return false;
		}
	}
	
	public double limitingHeight(double joystick) {
		if(getElevatorPosition() >= Constants.ELEVATOR_TOP_THRESHOLD && joystick > 0) {
			return 0.0;
		} else if(getElevatorPosition() <= Constants.ELEVATOR_BOTTOM_THRESHOLD && joystick < 0) {
			return 0.0;
		} else {
			return joystick;
		}
	}

	public void teleopElevator() {
		if (Math.abs(Robot.oi.manip.getLeftJoystick_Y()) > 0) {
			fineMovement(limitingHeight(Robot.oi.manip.getLeftJoystick_Y()));
		} else {
			left1.neutralOutput();
			setElevator(getElevatorPosition());
		}
	}
	
	public void JoystickElevator() {
		left1.set(ControlMode.PercentOutput, Robot.oi.manip.getLeftJoystick_Y());
		right1.set(ControlMode.PercentOutput, Robot.oi.manip.getLeftJoystick_Y());
		
	}
}
