/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.auton.grp_ExecuteSavedMacro;
import org.usfirst.frc.team226.robot.auton.grp_ToggleAutonRecording;
import org.usfirst.frc.team226.robot.commands.ArmSetpointGround;
import org.usfirst.frc.team226.robot.commands.ArmSetpointHigh;
import org.usfirst.frc.team226.robot.commands.ArmSetpointSwitch;
import org.usfirst.frc.team226.robot.commands.HardZeroArmEncoder;
import org.usfirst.frc.team226.robot.commands.ShiftDriveTrainHighGear;
import org.usfirst.frc.team226.robot.commands.ShiftDriveTrainLowGear;
import org.usfirst.frc.team226.robot.commands.ShiftIntake;
import org.usfirst.frc.team226.robot.commands.Shoot;
import org.usfirst.frc.team226.robot.commands.auto_Intake;
import org.usfirst.frc.team226.robot.commands.grp_ShootOuttake;

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
		driver.getLSButton().whenPressed(new ShiftDriveTrainHighGear());
		driver.getRSButton().whenPressed(new ShiftDriveTrainLowGear());

		// Intake
		manip.getRBButton().whenPressed(new ShiftIntake());
		manip.getRSButton().whileHeld(new auto_Intake());
		manip.getBButton().whenPressed(new grp_ShootOuttake());
		manip.getSTARTButton().whenPressed(new Shoot());

		// Arm
		manip.getSELECTButton().whenPressed(new HardZeroArmEncoder());
		manip.getYButton().whenPressed(new ArmSetpointHigh());
		manip.getXButton().whenPressed(new ArmSetpointSwitch());
		manip.getAButton().whenPressed(new ArmSetpointGround());

		// Auton recording controls
		driver.getSTARTButton().whenPressed(new grp_ToggleAutonRecording());
		driver.getSELECTButton().whenPressed(new grp_ExecuteSavedMacro());
	}
}
