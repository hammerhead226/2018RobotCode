package org.usfirst.frc.team226.robot;

public class Constants {
	//Arm PID Setpoints
	public static final double SCALE_2 = 0;
	public static final double SCALE_1 = 0;
	public static final double SWITCH_2 = 0;
	public static final double SWITCH_1 = 0;
	public static final double GROUND_2 = 0;
	public static final double GROUND_1 = 0;
	
	//Subsystem Voltage Limits
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final double CARRIAGE_VOLTAGE_LIMIT = 0;
	public static final double ARM_VOLTAGE_LIMIT = 0;
	
	//Enable Subsystem Voltage Limits
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;
	public static final boolean CARRIAGE_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean ARM_VOLTAGE_LIMIT_ENABLED = false;
	
	//Subsystem Current Limits
	public static final int CARRIAGE_CURRENT_LIMIT = 0;
	public static final int ARM_CURRENT_LIMIT = 0;
	public static final int DT_CURRENT_LIMIT = 30;
	
	//Enable Subsystem Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = true;
	public static final boolean CARRIAGE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean ARM_CURRENT_LIMIT_ENABLED = false;
	
	//Invert Motors
	public static final boolean CARRIAGE_INVERT_L = false;
	public static final boolean CARRIAGE_INVERT_R = false;
	public static final boolean ARM_INVERT_L = false;
	public static final boolean ARM_INVERT_R = false;
	
	//PID Slots
	public static final int DT_LEFT_PIDSLOT_IDX = 0;
	public static final int DT_RIGHT_PIDSLOT_IDX = 0;
	public static final int ARM_PIDSLOT_IDX = 0;
	
	//Misc
	public static final int STARTUP_WAIT = 10;
	public static final double CARRIAGE_OUTTAKE_SPEED = 0;
	public static final double CARRIAGE_INTAKE_SPEED = 0;
	public static final double CARRIAGE_GROUND_TOLERANCE = 0;
	public static final int ARM_SENSOR_TIMEOUT = 0;
	public static final int CARRIAGE_SENSOR_TIMEOUT = 0;



}
