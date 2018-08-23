package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.C_DriveClimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	TalonSRX climber = new TalonSRX(RobotMap.CLIMBER);

	public void initDefaultCommand() {
		setDefaultCommand(new C_DriveClimber());
	}

	public void driveClimber(double speed) {
		climber.set(ControlMode.PercentOutput, speed);
	}
}
