/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.commands.HardZeroElevator;
import org.usfirst.frc.team226.robot.commands.MoveElevatorDown;
import org.usfirst.frc.team226.robot.commands.MoveElevatorUp;

import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Controller driver = new Controller(0);
	public Controller manip = new Controller(1);
	
	public OI(){
		manip.getRBButton().whenPressed(new MoveElevatorUp());
		manip.getLBButton().whenPressed(new MoveElevatorDown());
		manip.getAButton().whenPressed(new HardZeroElevator());
	}
	
}
