package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

    TalonSRX left = new TalonSRX(RobotMap.CARRIAGE_LEFT_ID);
    TalonSRX right = new TalonSRX(RobotMap.CARRIAGE_RIGHT_ID);
    
    public Carriage() {
    	left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ELEVATOR_PID_IDX, Constants.ELEVATOR_TIMEOUT_MS);
  		
    	left.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
    	right.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, 0);
    	 		
    	left.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);	
    	right.enableCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT_ENABLED);
    	
    	left.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
    	right.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_LIMIT, 0);
    	
    	left.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
    	right.enableVoltageCompensation(Constants.ELEVATOR_VOLTAGE_LIMIT_ENABLED);
    	
    	left.setInverted(Constants.ELEVATOR_INVERT_L);
    	right.setInverted(Constants.ELEVATOR_INVERT_R);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void carriageOuttake() {
    	left.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
    	right.set(ControlMode.PercentOutput, Constants.CARRIAGE_OUTTAKE_SPEED);
    }
    
    public void setCarriageSpeed(double speed) {
    	left.set(ControlMode.PercentOutput, speed);
    	right.set(ControlMode.PercentOutput, speed);
    }
    
    public void setCarriageSpeed(double left, double right) {
    	this.left.set(ControlMode.PercentOutput, left);
    	this.right.set(ControlMode.PercentOutput, right);	
    }
}

