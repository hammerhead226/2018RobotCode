/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.commands.ElevatorToIntake;
import org.usfirst.frc.team226.robot.commands.ElevatorToPower;
import org.usfirst.frc.team226.robot.commands.ElevatorToScale;
import org.usfirst.frc.team226.robot.commands.ElevatorToSwitch;
import org.usfirst.frc.team226.robot.commands.HardZeroElevator;
import org.usfirst.frc.team226.robot.commands.RunIntakeCarriageIn;
import org.usfirst.frc.team226.robot.commands.Shift;

import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Controller driver = new Controller(RobotMap.DRIVER_CONTROLLER, Constants.DRIVER_DEADBAND);
	public Controller manip = new Controller(RobotMap.MANIP_CONTROLLER, Constants.MANIP_DEADBAND);

	public OI() {
		manip.getYButton().whenPressed(new ElevatorToSwitch());
		manip.getXButton().whenPressed(new ElevatorToScale());
		manip.getBButton().whenPressed(new ElevatorToPower());
		manip.getAButton().whenPressed(new ElevatorToIntake());
		manip.getRBButton().whenPressed(new HardZeroElevator());

		driver.getAButton().whenPressed(new Shift());
		// driver.getBButton().whenPressed(new );
		driver.getRBButton().whenPressed(new RunIntakeCarriageIn());

	}

}
