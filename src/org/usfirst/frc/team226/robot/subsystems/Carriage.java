package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

    private TalonSRX right = new TalonSRX(RobotMap.CR_RIGHT);
    private TalonSRX left = new TalonSRX(RobotMap.CR_LEFT);
    
    double speed;
    
    public Carriage() {
    	right.setInverted(true);
    }
    
    public void pushOut() {
    	right.set(ControlMode.PercentOutput, speed);
    	left.set(ControlMode.PercentOutput, speed);
    }

    public void initDefaultCommand() {
    }
}

