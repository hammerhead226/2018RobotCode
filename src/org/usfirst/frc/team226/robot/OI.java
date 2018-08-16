/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.auton.ExecuteMacro;
import org.usfirst.frc.team226.robot.auton.grp_ToggleAutonRecording;
import org.usfirst.frc.team226.robot.commands.E_HardZeroEncoder;
import org.usfirst.frc.team226.robot.commands.I_Intake;
import org.usfirst.frc.team226.robot.commands.I_Outtake;
import org.usfirst.frc.team226.robot.commands.PS_CompressorOff;
import org.usfirst.frc.team226.robot.commands.PS_CompressorOn;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainHighGear;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainLowGear;
import org.usfirst.frc.team226.robot.commands.PS_ToggleIntake;

import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Controller driver = new Controller(0);
	public Controller manip = new Controller(1);

	public OI() {
		// Drivetrain
		driver.getLSButton().whenPressed(new PS_ShiftDriveTrainHighGear());
		driver.getRSButton().whenPressed(new PS_ShiftDriveTrainLowGear());
		driver.getYButton().whenPressed(new PS_CompressorOn());
		driver.getBButton().whenPressed(new PS_CompressorOff());

		// Intake
		manip.getRSButton().whileHeld(new I_Intake());
		manip.getBButton().whenPressed(new I_Outtake());
		
		manip.getSTARTButton().whenPressed(new E_HardZeroEncoder());

		// Auton recording controls
		driver.getSTARTButton().whenPressed(new grp_ToggleAutonRecording());
		driver.getSELECTButton().whenPressed(new ExecuteMacro());
		
		manip.getRBButton().whenPressed(new PS_ToggleIntake());
		
	}

}
