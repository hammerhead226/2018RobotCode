/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int DT_FRONT_LEFT = 12;
	public static final int DT_FRONT_RIGHT = 16;
	public static final int DT_CENTER_LEFT = 11;
	public static final int DT_CENTER_RIGHT = 15;
	public static final int DT_REAR_LEFT = 13;
	public static final int DT_REAR_RIGHT = 17;
	
	public static final int CR_RIGHT = 0;
	public static final int CR_LEFT = 0;
	
	public static final int COMPRESSOR_A = 11;
	public static final int COMPRESSOR_B = 12;
	
	public static final int COMPRESSOR_PORT = COMPRESSOR_B;
	
	public static final int LEFT_SHIFTER_PCM = COMPRESSOR_B;
	public static final int LEFT_SHIFTER_1 = 4;
	public static final int LEFT_SHIFTER_2 = 5;
	public static final int RIGHT_SHIFTER_PCM = COMPRESSOR_B;
	public static final int RIGHT_SHIFTER_1 = 6;
	public static final int RIGHT_SHIFTER_2 = 7;

}