package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private TalonSRX frontLeft = new TalonSRX(RobotMap.EL_FRONT_LEFT);
	private TalonSRX rearLeft = new TalonSRX(RobotMap.EL_REAR_LEFT);
	private TalonSRX frontRight = new TalonSRX(RobotMap.EL_FRONT_RIGHT);
	private TalonSRX rearRight = new TalonSRX(RobotMap.EL_REAR_RIGHT);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

