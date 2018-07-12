package org.usfirst.frc.team226.robot;

public class Constants {

	// Subsystem Voltage Limits
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final double INTAKE_VOLTAGE_LIMIT = 0;

	public static final double WRIST_VOLTAGE_LIMIT = 0;

	public static final double ELEVATOR_VOLTAGE_LIMIT = 0;


	// Enable Subsystem Voltage Limits
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;
	public static final boolean INTAKE_VOLTAGE_LIMIT_ENABLED = false;

	public static final boolean WRIST_VOLTAGE_LIMIT_ENABLED = false;

	public static final boolean ELEVATOR_VOLTAGE_LIMIT_ENABLED = false;


	// Subsystem Current Limits
	public static final int INTAKE_CURRENT_LIMIT = 0;
	public static final int DT_CURRENT_LIMIT = 30;

	public static final int WRIST_CURRENT_LIMIT = 0;
	
	// Enable Subsystem Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = false;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean WRIST_CURRENT_LIMIT_ENABLED = false;

	public static final int ELEVATOR_CURRENT_LIMIT = 0;

	// Enable Subsystem Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = false;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean ELEVATOR_CURRENT_LIMIT_ENABLED = false;


	// Invert Motors
	public static final boolean INTAKE_INVERT_L = false;
	public static final boolean INTAKE_INVERT_R = false;

	public static final boolean WRIST_INVERT_L = false;
	public static final boolean WRIST_INVERT_R = false;

	public static final boolean ELEVATOR_INVERT_L = true;
	public static final boolean ELEVATOR_INVERT_R = true;


	// PID Slots
	public static final int DT_LEFT_PIDSLOT_IDX = 0;
	public static final int DT_RIGHT_PIDSLOT_IDX = 0;
	public static final int ELEVATOR_PIDSLOT_IDX = 0;


	// Misc
	public static final int DT_TIMEOUT = 10;
	public static final int INTAKE_TIMEOUT = 0;

	public static final int WRIST_TIMEOUT = 0;

	public static final int ELEVATOR_TIMEOUT = 0;

	public static final boolean DT_LEFT_SENSOR_PHASE = true;
	public static final boolean DT_RIGHT_SENSOR_PHASE = true;
	public static final double DT_VOLTAGE_RAMP_RATE = 0.1;
	public static final boolean ELEVATOR_SENSOR_PHASE = false;
	public static final boolean ELEVATOR_FORWARD_LIMIT_ENABLED = false;
	public static final boolean ELEVATOR_REVERSE_LIMIT_ENABLED = false;


	public static boolean IS_AUTON = false;
}
