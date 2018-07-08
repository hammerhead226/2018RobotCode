package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private TalonSRX left1 = new TalonSRX(RobotMap.ELEVATOR_LEFT_1);
	private TalonSRX left2 = new TalonSRX(RobotMap.ELEVATOR_LEFT_2);
	private TalonSRX right1 = new TalonSRX(RobotMap.ELEVATOR_RIGHT_1);
	private TalonSRX right2 = new TalonSRX(RobotMap.ELEVATOR_RIGHT_2);

	private int setpointPosition = ElevatorSetpoint.GROUND.position;

	public Elevator() {
		left2.follow(left1);
		right1.follow(left1);
		right2.follow(left1);

		left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PIDSLOT_IDX,
				Constants.ELEVATOR_TIMEOUT);

		left1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
		left2.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
		right1.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
		right2.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);

		left1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		left2.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right1.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
		right2.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);

		left1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		left2.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		right1.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
		right2.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);

		left1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		left2.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right1.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
		right2.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);

		left1.setInverted(Constants.ELEVATOR_INVERT_L);
		left2.setInverted(Constants.ELEVATOR_INVERT_L);
		right1.setInverted(Constants.ELEVATOR_INVERT_R);
		right2.setInverted(Constants.ELEVATOR_INVERT_R);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public enum ElevatorSetpoint {
		// setpoints to be set next week
		GROUND(0), POWER(0), SWITCH(0), SCALE(0);
		public int position;

		private ElevatorSetpoint(int position) {
			this.position = position;
		}
	}

	public void setElevatorSetpoint(ElevatorSetpoint s) {
		setpointPosition = s.position;
	}
	
	public int getElevatorPos() {
		return left1.getSelectedSensorPosition(Constants.ELEVATOR_PIDSLOT_IDX);
	}
	
	public void controlElevator(double speed) {
		if (speed != 0) {
			left1.set(ControlMode.PercentOutput, speed);
			setpointPosition = getElevatorPos();
		} else {
			left1.set(ControlMode.Position, setpointPosition);
		}
	}

}
