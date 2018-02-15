package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private TalonSRX left = new TalonSRX(RobotMap.INTAKE_LEFT);
    private TalonSRX right = new TalonSRX(RobotMap.INTAKE_RIGHT);
    
    
    public Intake() {
    	
    	left.setInverted(false);
    	right.setInverted(true);
    	
    	left.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.STARTUP_WAIT);
    	right.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.STARTUP_WAIT);
    	left.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
    	right.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
    	
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveIntake(double speed) {
    	left.set(ControlMode.PercentOutput, speed);
    	right.set(ControlMode.PercentOutput, speed);
    }
    
    public void moveIntake(double left, double right) {
    	this.left.set(ControlMode.PercentOutput, left);
    	this.right.set(ControlMode.PercentOutput, right);
    }
    
    public void outtake() {
    		left.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
    		right.set(ControlMode.PercentOutput, Constants.INTAKE_OUTTAKE_SPEED);
    }
}