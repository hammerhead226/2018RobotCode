/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.commands.ArmToGroundBack;
import org.usfirst.frc.team226.robot.commands.ArmToGroundFront;
import org.usfirst.frc.team226.robot.commands.ArmToPowerBack;
import org.usfirst.frc.team226.robot.commands.ArmToPowerFront;
import org.usfirst.frc.team226.robot.commands.ArmToScaleBack;
import org.usfirst.frc.team226.robot.commands.ArmToScaleFront;
import org.usfirst.frc.team226.robot.commands.ArmToSwitchBack;
import org.usfirst.frc.team226.robot.commands.ArmToSwitchFront;
import org.usfirst.frc.team226.robot.commands.HardZeroArmEncoder;
import org.usfirst.frc.team226.robot.commands.IntakeOpenCloseLeft;
import org.usfirst.frc.team226.robot.commands.IntakeOpenCloseRight;
import org.usfirst.frc.team226.robot.commands.Shift;

import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Controller driver = new Controller(0);
	public Controller manip = new Controller(1);
	
	public OI() {
		driver.getRBButton().whenPressed(new Shift());
		manip.getLBButton().whenPressed(new HardZeroArmEncoder());
		driver.getAButton().whenPressed(new IntakeOpenCloseRight());
		driver.getBButton().whenPressed(new IntakeOpenCloseLeft());
		if(manip.getRBButtonPressed()) {
			manip.getAButton().whenPressed(new ArmToGroundBack());
			manip.getBButton().whenPressed(new ArmToPowerBack());
			manip.getXButton().whenPressed(new ArmToSwitchBack());
			manip.getYButton().whenPressed(new ArmToScaleBack());
		}else if(manip.getLBButtonPressed()) {
			manip.getAButton().whenPressed(new ArmToGroundFront());
			manip.getBButton().whenPressed(new ArmToPowerFront());
			manip.getXButton().whenPressed(new ArmToSwitchFront());
			manip.getYButton().whenPressed(new ArmToScaleFront());
		}
	}
}
