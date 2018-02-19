package org.usfirst.frc.team226.robot;

public class Constants {
	
	public static final double MANIP_DEADBAND = 0.2;
	public static final double DRIVER_DEADBAND = 0.2;
	
	public static final int STARTUP_WAIT = 10;
	
	public static final double DT_VOLTAGE_LIMIT = 11;
	public static final int DT_CURRENT_LIMIT = 30;
	
	public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;
	public static final boolean DT_CURRENT_LIMIT_ENABLED = true;

	public static final int INTAKE_CURRENT_LIMIT = 30;
	public static final boolean INTAKE_CURRENT_LIMIT_ENABLED = true;
	public static final double INTAKE_OUTTAKE_SPEED = 0.5;	
	public static final boolean INTAKE_INVERT_RIGHT = true;
	public static final boolean INTAKE_INVERT_LEFT = false;
	public static final double INTAKE_INTAKE_SPEED = 0;

	public static final double CARRIAGE_OUTTAKE_SPEED = 0;
	public static final double CARRIAGE_INTAKE_SPEED = 0;
	public static final boolean CARRIAGE_INVERT_RIGHT = true;
	public static final boolean CARRIAGE_INVERT_LEFT = false;
	public static final double CARRIAGE_PHOTOEYE_THRESHHOLD = 0;

	public static final boolean ELEVATOR_VOLTAGE_LIMIT_ENABLED = true;
	
	public static final double ELEVATOR_INTAKE_HEIGHT = 0;
	public static final double ELEVATOR_SWITCH_HEIGHT = 0;
	public static final double ELEVATOR_POW_HEIGHT = 0;
	public static final double ELEVATOR_SCALE_HEIGHT = 0;
	public static final double ELEVATOR_VOLTAGE_LIMIT = 11;
	public static final boolean ELEVATOR_INVERT_L = false;
	public static final boolean ELEVATOR_INVERT_R = true;
	public static final int ELEVATOR_PID_IDX = 0;
	public static final int ELEVATOR_TIMEOUT_MS = 0;
	public static final double ELEVATOR_FINE_TUNE = 0.5;
	public static final long ELEVATOR_ON_TARGET_S = 0;
	public static final int ELEVATOR_ERROR_MARGIN = 0;
	public static final int ELEVATOR_CURRENT_LIMIT = 0;
	public static final boolean ELEVATOR_CURRENT_LIMIT_ENABLED = true;
	
}
