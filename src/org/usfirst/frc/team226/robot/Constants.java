package org.usfirst.frc.team226.robot;

public class Constants {

	// Arm PID Tolerance
	public static final int ARM_ERROR_TOLERANCE = 50;

	// Arm Soft Limits
	public static final boolean ARM_FORWARD_LIMIT_ENABLED = false;
	public static final boolean ARM_REVERSE_LIMIT_ENABLED = false;

	// Subsystem Voltage Limits
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final double INTAKE_VOLTAGE_LIMIT = 0;
	public static final double ARM_VOLTAGE_LIMIT = 0;
	public static final double CLIMBER_VOLTAGE_LIMIT = 0;

	// Enable Subsystem Voltage Limits
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;
	public static final boolean INTAKE_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean ARM_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean CLIMBER_VOLTAGE_LIMIT_ENABLED = false;

	// Subsystem Current Limits
	public static final int INTAKE_CURRENT_LIMIT = 0;
	public static final int ARM_CURRENT_LIMIT = 0;
	public static final int DT_CURRENT_LIMIT = 30;
	public static final int CLIMBER_CURRENT_LIMIT = 0;

	// Enable Subsystem Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = false;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean ARM_CURRENT_LIMIT_ENABLED = false;
	public static final boolean CLIMBER_CURRENT_LIMIT_ENABLED = false;

	// Invert Motors
	public static final boolean INTAKE_INVERT_L = true;
	public static final boolean INTAKE_INVERT_R = false;
	public static final boolean ARM_INVERT_R = true;
	public static final boolean ARM_INVERT_L = true;
	public static final boolean ROLLER_INVERT = true;
	public static final boolean CLIMBER_INVERT_R = true;
	public static final boolean CLIMBER_INVERT_L = false;

	// PID Slots
	public static final int DT_LEFT_PIDSLOT_IDX = 0;
	public static final int DT_RIGHT_PIDSLOT_IDX = 0;
	public static final int ARM_PIDSLOT_IDX = 0;

	// Misc
	public static final int DT_TIMEOUT = 10;
	public static final int ARM_TIMEOUT = 10;
	public static final int INTAKE_TIMEOUT = 0;
	public static final boolean ARM_SENSOR_PHASE = false;
	public static final boolean DT_LEFT_SENSOR_PHASE = true;
	public static final boolean DT_RIGHT_SENSOR_PHASE = true;
	public static final double ARM_MAX_SPEED = 0.75;
	public static final int CLIMBER_SENSOR_TIMEOUT = 10;
	public static final double CLIMBER_SPEED = 0;
	public static final double DT_VOLTAGE_RAMP_RATE = 0.3;

	public static final double INTAKE_PULL_IN_SPEED = 0.5;

	//Climber
	public static final double UNLOCKED_POSITION = 1;
	public static final double LOCKED_POSITION = 0.45;
}
