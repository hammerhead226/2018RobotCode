package org.usfirst.frc.team226.robot;

public class Constants {	
	//Controllers
	public static final double MANIP_DEADBAND = 0.2;
	public static final double DRIVER_DEADBAND = 0.2;
	
	//Voltage Limits
	public static final double ELEVATOR_VOLTAGE_LIMIT = 11;
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final double INTAKE_VOLTAGE_LIMIT = 11;
	public static final double CARRIAGE_VOLTAGE_LIMIT = 11;
	
	//Enable Voltage Limits
	public static final boolean ELEVATOR_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean CARRIAGE_VOLTAGE_LIMIT_ENABLED = false;
	public static final boolean INTAKE_VOLTAGE_LIMIT_ENABLED = false;
	
	//Current Limits
	public static final int INTAKE_CURRENT_LIMIT = 30;
	public static final int DT_CURRENT_LIMIT = 30;
	public static final int CARRIAGE_CURRENT_LIMIT = 30;
	public static final int ELEVATOR_CURRENT_LIMIT = 30;
	
	//Enable Current Limits
	public static final boolean DT_CURRENT_LIMIT_ENABLED = false;
	public static final boolean ELEVATOR_CURRENT_LIMIT_ENABLED = false;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = false;
	public static final boolean CARRIAGE_CURRENT_LIMIT_ENABLED = false;
	
	//PID Slots
	public static final int DT_LEFT_PIDSLOT_IDX = 0;
	public static final int DT_RIGHT_PIDSLOT_IDX = 0;
	public static final int ELEVATOR_PID_IDX = 0;
	
	//Motor Inversions
	public static final boolean ELEVATOR_INVERT_L1 = false;
	public static final boolean ELEVATOR_INVERT_L2 = false;
	public static final boolean ELEVATOR_INVERT_R1 = true;
	public static final boolean ELEVATOR_INVERT_R2 = false;
	public static final boolean INTAKE_INVERT_RIGHT = true;
	public static final boolean INTAKE_INVERT_LEFT = false;
	public static final boolean CARRIAGE_INVERT_RIGHT = false;
	public static final boolean CARRIAGE_INVERT_LEFT = false;
	
	//Motor Speeds
	public static final double CARRIAGE_OUTTAKE_SPEED = -0.5;
	public static final double CARRIAGE_INTAKE_SPEED = 0.5;
	public static final double INTAKE_OUTTAKE_SPEED = -0.5;	
	public static final double INTAKE_INTAKE_SPEED = 0.5;
	public static final double INTAKE_UN_JAM_SPEED = 0.5;
	
	//Elevator PID Setpoints
	public static final double ELEVATOR_INTAKE_HEIGHT = 0;
	public static final double ELEVATOR_SWITCH_HEIGHT = 10000;
	public static final double ELEVATOR_POW_HEIGHT = 20000;
	public static final double ELEVATOR_SCALE_HEIGHT = 30000;
	
	//Timeouts
	public static final int CARRIAGE_TIMEOUT_MS = 0;
	public static final long ELEVATOR_ON_TARGET_MS = 0;
	public static final double INTAKE_UN_JAM_TIMEOUT = 0.5;
	public static final int INTAKE_TIMEOUT_MS = 0;
	public static final int ELEVATOR_TIMEOUT_MS = 0;
	public static final double ELEVATOR_ON_TARGET_S = 5;
	public static final int STARTUP_WAIT = 10;
	
	//Soft Limits
	public static final int ELEVATOR_TOP_THRESHOLD = 50000;
	public static final int ELEVATOR_BOTTOM_THRESHOLD = 0;
	
	//Enable Soft Limits
	public static final boolean ELEVATOR_ENABLE_BOTTOM_THRESHOLD = false;
	public static final boolean ELEVATOR_ENABLE_TOP_THRESHOLD = false;
	
	//Misc
	public static final double CARRIAGE_PHOTOEYE_THRESHHOLD = 0;
	public static final double ELEVATOR_FINE_TUNE = 0.5;
	public static final int ELEVATOR_ERROR_MARGIN = 100;
	public static final double ELEVATOR_INTAKE_TOLERANCE = 1000;
	public static final double ELEVATOR_PHOTO_EYE_THRESHOLD = 0;
}
