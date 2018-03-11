/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.auton.ExecuteDoubleMacro;
import org.usfirst.frc.team226.robot.auton.grp_ExecuteSavedMacro;
import org.usfirst.frc.team226.robot.subsystems.Arm;
import org.usfirst.frc.team226.robot.subsystems.DriveTrain;
import org.usfirst.frc.team226.robot.subsystems.Intake;
import org.usfirst.frc.team226.robot.subsystems.PneumaticsSystem;
import org.usfirst.frc.team226.robot.vision.VisionRun;

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
	public VisionRun vision = new VisionRun();

	Command m_autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static OI oi;
	
	@Override
	public void robotInit() {
		arm = new Arm();
		driveTrain = new DriveTrain();
		pneumaticsSystem = new PneumaticsSystem();
		intake = new Intake();
		oi = new OI();
		chooser.addDefault("Baseline Cross", new grp_ExecuteSavedMacro("baseline_cross"));
		chooser.addObject("Left Switch", new ExecuteDoubleMacro("leftswitch_left", "leftswitch_right"));
		chooser.addObject("Center Switch", new ExecuteDoubleMacro("centerswitch_left", "centerswitch_right"));
		chooser.addObject("Right Switch", new ExecuteDoubleMacro("rightswitch_left", "rightswitch_right"));
		SmartDashboard.putData("Auto mode", chooser);
		// vision.start();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		m_autonomousCommand = chooser.getSelected();

		/*
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
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

}
