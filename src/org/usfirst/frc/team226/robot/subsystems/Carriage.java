package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

    private TalonSRX right = new TalonSRX(RobotMap.CARRIAGE_RIGHT);
    private TalonSRX left = new TalonSRX(RobotMap.CARRIAGE_LEFT);
    
    
    
    public Carriage() {
    	left.setInverted(Constants.CARRIAGE_INVERT_LEFT);
    	right.setInverted(Constants.CARRIAGE_INVERT_RIGHT);
    }
    
    public void pushOut() {
    	right.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
    	left.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
    }
    
    public void pullIn() {
    	right.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
    	left.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
    }

    public void initDefaultCommand() {
    }
}

