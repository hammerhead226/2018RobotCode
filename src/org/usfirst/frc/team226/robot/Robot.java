/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.usfirst.frc.team226.robot.auton.ExecuteChoiceMacro;
import org.usfirst.frc.team226.robot.auton.ExecuteMacro;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainHighGear;
import org.usfirst.frc.team226.robot.subsystems.Arm;
import org.usfirst.frc.team226.robot.subsystems.Climber;
import org.usfirst.frc.team226.robot.subsystems.DriveTrain;
import org.usfirst.frc.team226.robot.subsystems.Intake;
import org.usfirst.frc.team226.robot.subsystems.PneumaticsSystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Arm arm;
	public static DriveTrain driveTrain;
	public static PneumaticsSystem pneumaticsSystem;
	public static Intake intake;
	public static Climber climber;
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		arm = new Arm();
		driveTrain = new DriveTrain();
		pneumaticsSystem = new PneumaticsSystem();
		climber = new Climber();
		intake = new Intake();
		oi = new OI();
		chooser.addDefault("Baseline Cross", new ExecuteMacro("baseline"));
		chooser.addObject("Left Switch", new ExecuteDoubleMacro("left", "baseline"));
		chooser.addObject("Center Switch", new ExecuteDoubleMacro("centerswitch_left", "centerswitch_right"));
		chooser.addObject("Right Switch", new ExecuteDoubleMacro("baseline", "rightswitch_right"));
		SmartDashboard.putData("Auto mode", chooser);

		SmartDashboard.putData(new PS_ShiftDriveTrainHighGear());

		ProfileParser.cache("centerswitch_left");
		ProfileParser.cache("centerswitch_left_pickup_fast");
		ProfileParser.cache("centerswitch_left_twocube");
		ProfileParser.cache("centerswitch_right");
		ProfileParser.cache("baseline");
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Arm Encoder", arm.getArmPos());
	}

	@Override
	public void disabledInit() {
		Constants.IS_AUTON = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Constants.IS_AUTON = true;
		driveTrain.disableVoltageRamping();
		SmartDashboard.putString("Field State", DriverStation.getInstance().getGameSpecificMessage());
		m_autonomousCommand = chooser.getSelected();

		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		driveTrain.enableVoltageRamping();
		Constants.IS_AUTON = false;
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

}
