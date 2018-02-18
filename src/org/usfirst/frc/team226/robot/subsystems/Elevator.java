package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ElevatorPID;

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
		setDefaultCommand(new ElevatorPID());
	}

	public Elevator() {

		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PID_IDX,
				Constants.ELEVATOR_TIMEOUT_MS);

		left.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);
		right.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);

		left.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);
		right.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT_MS);

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

	public void setElevator(double height) {
		left.set(ControlMode.MotionMagic, height);
	}

	public void zeroEncoder() {
		if (hallEffect.get()) {
			left.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
		}
	}

	public void hardZeroEncoder() {
		left.setSelectedSensorPosition(0, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
	}

	public void fineMovement(double speed) {
		left.set(ControlMode.MotionMagic, getElevatorSetpoint() + speed * Constants.ELEVATOR_FINE_TUNE);
	}

	public int getElevatorError() {
		return left.getClosedLoopError(Constants.ELEVATOR_PID_IDX);
	}

	public double getElevatorSetpoint() {
		return left.getActiveTrajectoryPosition();
	}

	public boolean isFinished() {

		if (Math.abs(Robot.elevator.getElevatorError()) < Constants.ELEVATOR_ERROR_MARGIN) {
			return true;
		} else {
			return false;
		}
	}

	public void teleopElevator() {
		if (Math.abs(Robot.m_oi.manip.getLeftJoystick_Y()) > 0) {
			Robot.elevator.fineMovement(Robot.m_oi.manip.getLeftJoystick_Y());
		} else {
			if (Robot.m_oi.manip.getAButtonPressed()) {
				Robot.elevator.setElevator(ElevatorHeight.INTAKE);
			} else if (Robot.m_oi.manip.getBButtonPressed()) {
				Robot.elevator.setElevator(ElevatorHeight.SWITCH);
			} else if (Robot.m_oi.manip.getXButtonPressed()) {
				Robot.elevator.setElevator(ElevatorHeight.POWER);
			} else if (Robot.m_oi.manip.getYButtonPressed()) {
				Robot.elevator.setElevator(ElevatorHeight.SCALE);
			} else {
				Robot.elevator.setElevator(Robot.elevator.getElevatorSetpoint());
			}
		}
	}
}
