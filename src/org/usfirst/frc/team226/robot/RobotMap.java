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

	public static final int INTAKE_LEFT = 14;
	public static final int INTAKE_RIGHT = 18;
	public static final int INTAKE_ROLLERS = 20;
	public static final int ARM_LEFT = 19;
	public static final int ARM_RIGHT = 21;

	public static final int CLIMBER_LEFT = 11;
	public static final int CLIMBER_RIGHT = 15;
	public static final int CLIMBER_SERVO = 0;

	public static final int PCM_A = 11;
	public static final int PCM_B = 12;

	public static final int COMPRESSOR_PORT = PCM_B;

	public static final int DT_LEFT_SHIFTER_PCM = PCM_B;
	public static final int DT_LEFT_SHIFTER_1 = 0;
	public static final int DT_LEFT_SHIFTER_2 = 1;
	public static final int DT_RIGHT_SHIFTER_PCM = PCM_B;
	public static final int DT_RIGHT_SHIFTER_1 = 2;
	public static final int DT_RIGHT_SHIFTER_2 = 3;

	public static final int ARM_PCM = PCM_A;
	public static final int SHOOTER_PISTON_1 = 4;
	public static final int SHOOTER_PISTON_2 = 5;
	public static final int SHOOTER_PANCAKE_1 = 0;
	public static final int SHOOTER_PANCAKE_2 = 1;
	public static final int INTAKE_PCM = PCM_A;
	public static final int INTAKE_SHIFTER_1 = 2;
	public static final int INTAKE_SHIFTER_2 = 3;
	public static final int INTAKE_ROLLER_SHIFTER_1 = 6;
	public static final int INTAKE_ROLLER_SHIFTER_2 = 7;
	
}
