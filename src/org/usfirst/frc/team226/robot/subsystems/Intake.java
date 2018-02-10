package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *aUTh0R = n1Dh1 j@1$0n
 */
public class Intake extends Subsystem {

    public TalonSRX left = new TalonSRX(RobotMap.IN_LEFT);
    public TalonSRX right = new TalonSRX(RobotMap.IN_RIGHT);
    
    DoubleSolenoid flip = new DoubleSolenoid(RobotMap.IN_SOLE_FW,RobotMap.IN_SOLE_RV);
    
    public Intake() {
    	
    	right.setInverted(true);
    	
    	left.configContinuousCurrentLimit(Constants.IN_CURRENT_LIMIT, Constants.STARTUP_WAIT);
    	right.configContinuousCurrentLimit(Constants.IN_CURRENT_LIMIT, Constants.STARTUP_WAIT);
    	left.enableCurrentLimit(Constants.IN_CURRENT_LIMIT_ENABLED);
    	right.enableCurrentLimit(Constants.IN_CURRENT_LIMIT_ENABLED);
    	
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveIntake(double left, double right) {
    	this.left.set(ControlMode.PercentOutput, left);
    	this.right.set(ControlMode.PercentOutput, right);
    }
    
    public void moveOuttake(boolean move) {
    	if (move) {
    		left.set(ControlMode.PercentOutput, Constants.IN_OUTTAKE_SPEED);
    		right.set(ControlMode.PercentOutput, Constants.IN_OUTTAKE_SPEED);
    	}
    }
}

