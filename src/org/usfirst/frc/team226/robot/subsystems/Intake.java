package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    public TalonSRX left = new TalonSRX(RobotMap.IN_LEFT);
    public TalonSRX right = new TalonSRX(RobotMap.IN_RIGHT);
    
    DoubleSolenoid flip = new DoubleSolenoid(0,0);
    
    public Intake() {
    	
    	right.setInverted(true);
    	
    	left.configVoltageCompSaturation(11.5, 0);
    	right.configVoltageCompSaturation(11.5, 0);
    	left.enableVoltageCompensation(true);
    	right.enableVoltageCompensation(true);
    	
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveIntake(double left, double right) {
    	this.left.set(ControlMode.PercentOutput, left);
    	this.right.set(ControlMode.PercentOutput, right);
    }
    
    public void moveOutake(boolean move) {
    	if (move) {
    		left.set(ControlMode.PercentOutput, 0);
    		right.set(ControlMode.PercentOutput, 0);
    	}
    }
}

