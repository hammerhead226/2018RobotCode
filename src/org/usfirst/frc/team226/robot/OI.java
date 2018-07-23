/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.auton.ExecuteMacro;
import org.usfirst.frc.team226.robot.auton.grp_ToggleAutonRecording;
import org.usfirst.frc.team226.robot.commands.A_DriveArm;
import org.usfirst.frc.team226.robot.commands.A_HardZeroArmEncoder;
import org.usfirst.frc.team226.robot.commands.A_SetEncoderToArmUp;
import org.usfirst.frc.team226.robot.commands.A_SetpointGround;
import org.usfirst.frc.team226.robot.commands.A_SetpointHigh;
import org.usfirst.frc.team226.robot.commands.A_SetpointSwitch;
import org.usfirst.frc.team226.robot.commands.C_UnlockClimber;
import org.usfirst.frc.team226.robot.commands.I_Intake;
import org.usfirst.frc.team226.robot.commands.I_Outtake;
import org.usfirst.frc.team226.robot.commands.I_grp_OuttakeShoot;
import org.usfirst.frc.team226.robot.commands.PS_CompressorOff;
import org.usfirst.frc.team226.robot.commands.PS_CompressorOn;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainHighGear;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainLowGear;
import org.usfirst.frc.team226.robot.commands.PS_ShiftIntake;
import org.usfirst.frc.team226.robot.commands.PS_grp_Reload;

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
		driver.getRBButton().whileHeld(new A_DriveArm());

		// Intake
		manip.getRBButton().whenPressed(new PS_ShiftIntake());
		manip.getRSButton().whileHeld(new I_Intake());
		manip.getBButton().whenPressed(new I_Outtake());

		// Arm
		manip.getYButton().whenPressed(new A_SetpointHigh());
		manip.getXButton().whenPressed(new A_SetpointSwitch());
		manip.getAButton().whenPressed(new A_SetpointGround());
		manip.getSTARTButton().whenPressed(new A_SetEncoderToArmUp());
		manip.getSELECTButton().whenPressed(new A_HardZeroArmEncoder());
		
		//Shooter
		driver.getAButton().whenPressed(new I_grp_OuttakeShoot());
		driver.getXButton().whenPressed(new PS_grp_Reload());
		
		// Auton recording controls
		driver.getSTARTButton().whenPressed(new grp_ToggleAutonRecording());
		driver.getSELECTButton().whenPressed(new ExecuteMacro());
		
		//Climber
		driver.getLBButton().whenPressed(new C_UnlockClimber());
	}

}
