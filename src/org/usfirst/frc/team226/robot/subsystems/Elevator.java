package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.E_ControlElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private TalonSRX elevator1 = new TalonSRX(RobotMap.ELEVATOR_1);
	private TalonSRX elevator2 = new TalonSRX(RobotMap.ELEVATOR_2);
	private TalonSRX elevator3 = new TalonSRX(RobotMap.ELEVATOR_3);
	private TalonSRX elevator4 = new TalonSRX(RobotMap.ELEVATOR_4);

	private int setpointPosition = ElevatorSetpoint.GROUND.position;

	public Elevator() {

		elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PIDSLOT_IDX,
				Constants.ELEVATOR_TIMEOUT);

		hardZeroEncoder();

		elevator1.setSensorPhase(Constants.ELEVATOR_SENSOR_PHASE);

		elevator1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator2.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator3.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator4.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, Constants.ELEVATOR_TIMEOUT);

		elevator1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		elevator2.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		elevator3.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		elevator4.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);

		elevator1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator2.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator3.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);
		elevator4.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, Constants.ELEVATOR_TIMEOUT);

		elevator1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		elevator2.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		elevator3.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		elevator4.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		elevator1.setInverted(!Constants.ELEVATOR_INVERT);
		elevator2.setInverted(Constants.ELEVATOR_INVERT);
		elevator3.setInverted(!Constants.ELEVATOR_INVERT);
		elevator4.setInverted(Constants.ELEVATOR_INVERT);

		elevator1.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		elevator2.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		elevator3.configForwardSoftLimitEnable(Constants.ELEVATOR_FORWARD_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);
		elevator4.configReverseSoftLimitEnable(Constants.ELEVATOR_REVERSE_LIMIT_ENABLED, Constants.ELEVATOR_TIMEOUT);

//		elevator2.follow(elevator1);
//		elevator3.follow(elevator1);
//		elevator4.follow(elevator1);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new E_ControlElevator());
	}

	public void log() {
		SmartDashboard.putNumber("11", elevator1.getMotorOutputPercent());
		SmartDashboard.putNumber("19", elevator2.getMotorOutputPercent());
		SmartDashboard.putNumber("15", elevator3.getMotorOutputPercent());
		SmartDashboard.putNumber("18", elevator4.getMotorOutputPercent());
		SmartDashboard.putNumber("elevator pos", setpointPosition);
		SmartDashboard.putNumber("legit elevator pos", getElevatorPos());
		SmartDashboard.putNumber("elevator 1", elevator1.getMotorOutputPercent());
	}

	public enum ElevatorSetpoint {
		// setpoints to be set next week
		GROUND(0), EXCHANGE(0), SWITCH(0), SCALE(0);
		public int position;

		private ElevatorSetpoint(int position) {
			this.position = position;
		}
	}

	public void setElevatorSetpoint(ElevatorSetpoint s) {
		setpointPosition = s.position;
	}

	public int getElevatorPos() {
		return elevator1.getSelectedSensorPosition(Constants.ELEVATOR_PIDSLOT_IDX);
	}

	public void hardZeroEncoder() {
		elevator1.setSelectedSensorPosition(0, Constants.ELEVATOR_PIDSLOT_IDX, Constants.ELEVATOR_TIMEOUT);
	}

	public void driveElevator(double speed) {
		if(speed == 0) {
			speed = Constants.ELEVATOR_CONTINOUS_CURRENT;
		}
		elevator1.set(ControlMode.PercentOutput, speed);
	}

	public void controlElevator(double speed) {
		if(speed == 0) {
			speed = Constants.ELEVATOR_CONTINOUS_CURRENT;
		}
		elevator1.set(ControlMode.PercentOutput, speed);
		setpointPosition = getElevatorPos();
	}

}
