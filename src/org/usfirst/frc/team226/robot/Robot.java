/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team226.robot;

import java.util.ArrayList;

import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.usfirst.frc.team226.robot.auton.ExecuteChoiceMacro;
import org.usfirst.frc.team226.robot.auton.ExecuteMacro;
import org.usfirst.frc.team226.robot.auton.grp_ExecuteMacroList;
import org.usfirst.frc.team226.robot.commands.PS_ShiftDriveTrainHighGear;
import org.usfirst.frc.team226.robot.subsystems.DriveTrain;
import org.usfirst.frc.team226.robot.subsystems.Elevator;
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
	public static DriveTrain driveTrain;
	public static PneumaticsSystem pneumaticsSystem;
	public static Intake intake;
	public static Elevator elevator;
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		pneumaticsSystem = new PneumaticsSystem();
		intake = new Intake();
		elevator = new Elevator();
		oi = new OI();
		
		chooser.addDefault("Baseline Cross", new ExecuteMacro("baseline"));
		chooser.addObject("Left Switch", new ExecuteChoiceMacro("leftswitch_left", "baseline"));
		chooser.addObject("Center Switch", new ExecuteChoiceMacro("centerswitch_left", "centerswitch_right"));
		chooser.addObject("Right Switch", new ExecuteChoiceMacro("baseline", "rightswitch_right"));
		
		ArrayList<String> left = new ArrayList<String>();
		left.add("centerswitch_left");
		left.add("centerswitch_left_pickup_fast");
		left.add("centerswitch_left_twocube");
		ArrayList<String> right = new ArrayList<String>();
		right.add("centerswitch_right");
		right.add("nothing");
		right.add("nothing");
		chooser.addObject("Center switch 2 cube?", new grp_ExecuteMacroList(left, right));
		
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
		SmartDashboard.putString("Field State", DriverStation.getInstance().getGameSpecificMessage());
		m_autonomousCommand = chooser.getSelected();

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
