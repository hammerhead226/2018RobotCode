package org.usfirst.frc.team226.robot;

public class Constants {
	//Arm PID Setpoints
	public static final int SCALE_BACK = 0;
	public static final int SCALE_FRONT = 0;
	public static final int SWITCH_BACK = 0;
	public static final int SWITCH_FRONT = 0;
	public static final int GROUND_BACK = 0;
	public static final int GROUND_FRONT = 0;
	
	//Arm Soft Limits
	public static final int ARM_FORWARD_LIMIT = 100000;
	public static final int ARM_REVERSE_LIMIT = -100000;
	
	//Enable Arm Soft Limits
	public static final boolean ARM_FORWARD_LIMIT_ENABLED = false;
	public static final boolean ARM_REVERSE_LIMIT_ENABLED = false;
	
	//Subsystem Voltage Limits
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final double INTAKE_VOLTAGE_LIMIT = 0;
	public static final double ARM_VOLTAGE_LIMIT = 0;
	
	//Enable Subsystem Voltage Limits
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;
	public static final boolean INTAKE_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean ARM_VOLTAGE_LIMIT_ENABLED = false;
	
	//Subsystem Current Limits
	public static final int INTAKE_CURRENT_LIMIT = 0;
	public static final int ARM_CURRENT_LIMIT = 0;
	public static final int DT_CURRENT_LIMIT = 30;
	
	//Enable Subsystem Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = true;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean ARM_CURRENT_LIMIT_ENABLED = false;
	
	//Invert Motors
	public static final boolean INTAKE_INVERT_L = false;
	public static final boolean CARRIAGE_INVERT_R = false;
	public static final boolean ARM_INVERT_L = false;
	public static final boolean ARM_INVERT_R = false;
	
	//PID Slots
	public static final int DT_LEFT_PIDSLOT_IDX = 0;
	public static final int DT_RIGHT_PIDSLOT_IDX = 0;
	public static final int ARM_PIDSLOT_IDX = 0;
	
	//Misc
	public static final int DT_TIMEOUT = 10;
	public static final int ARM_SENSOR_TIMEOUT = 0;
	public static final int INTAKE_SENSOR_TIMEOUT = 0;
	public static final long ARM_CLOSE_TIME = 0;
	public static final boolean ARM_SENSOR_PHASE = true;

}
