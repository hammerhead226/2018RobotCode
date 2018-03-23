package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private TalonSRX left = new TalonSRX(RobotMap.CLIMBER_LEFT);
    private TalonSRX right = new TalonSRX(RobotMap.CLIMBER_RIGHT);
    
    public Climber() {
    	left.configContinuousCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
    	right.configContinuousCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
    	
    	left.enableCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT_ENABLED);
    	right.enableCurrentLimit(Constants.CLIMBER_CURRENT_LIMIT_ENABLED);
    	
    	left.configVoltageCompSaturation(Constants.CLIMBER_VOLTAGE_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
    	right.configVoltageCompSaturation(Constants.CLIMBER_VOLTAGE_LIMIT, Constants.CLIMBER_SENSOR_TIMEOUT);
    	
    	left.enableVoltageCompensation(Constants.CLIMBER_VOLTAGE_LIMIT_ENABLED);
    	right.enableVoltageCompensation(Constants.CLIMBER_VOLTAGE_LIMIT_ENABLED);
    }

    public void initDefaultCommand() {
    	
    }
    
    public void runClimber(double speed) {
    	left.set(ControlMode.PercentOutput, speed);
    	right.set(ControlMode.PercentOutput, speed);
    }
}

