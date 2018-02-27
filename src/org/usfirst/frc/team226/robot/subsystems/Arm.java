package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    TalonSRX left = new TalonSRX(RobotMap.ARM_LEFT_ID);
    TalonSRX right = new TalonSRX(RobotMap.ARM_RIGHT_ID);
    
    public Arm() {
    	left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ARM_PID_IDX, Constants.ARM_SENSOR_TIMEOUT);
    	
    	left.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	right.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	 		
    	left.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);	
    	right.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
    	
    	left.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	right.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	
    	left.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    	right.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    	
    	left.setInverted(Constants.ARM_INVERT_L);
    	right.setInverted(Constants.ARM_INVERT_R);
    	
    	right.follow(left);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void armToGround1() {
    	left.set(ControlMode.MotionMagic, Constants.GROUND_1);
    }
    
    public void armToGround2() {
    	left.set(ControlMode.MotionMagic, Constants.GROUND_2);
    }
    
    public void armToSwitch1() {
    	left.set(ControlMode.MotionMagic, Constants.SWITCH_1);
    }
    
    public void armToSwitch2() {
    	left.set(ControlMode.MotionMagic, Constants.SWITCH_2);
    }
    
    public void armToScale1() {
    	left.set(ControlMode.MotionMagic, Constants.SCALE_1);
    }
    
    public void armToScale2() {
    	left.set(ControlMode.MotionMagic, Constants.SCALE_2);
    }
    
    public int getArmPos() {
    	return left.getSelectedSensorPosition(Constants.ARM_PID_IDX);
    }
    
    public void armHoldPos() {
    	left.set(ControlMode.MotionMagic, getArmPos());
    }
    
    public void manualArmMovement() {
    	if(Math.abs(Robot.m_oi.manip.getLeftJoystick_Y()) > 0) {
    		left.set(ControlMode.PercentOutput, Robot.m_oi.manip.getLeftJoystick_Y());
    	} else {
    		armHoldPos();
    	}
    }
   
    public void setArmPID() {
    	if(Robot.m_oi.manip.getAButtonPressed()) {
    		armToGround1();
    	} else if(Robot.m_oi.manip.getBButtonPressed()) {
    		armToSwitch1();
    	} else if(Robot.m_oi.manip.getYButtonPressed()) {
    		armToScale1();
    	} else if(Robot.m_oi.manip.getAButtonPressed() && Robot.m_oi.manip.getRBButtonPressed()) {
    		armToGround2();
    	} else if(Robot.m_oi.manip.getBButtonPressed() && Robot.m_oi.manip.getRBButtonPressed()) {
    		armToSwitch2();
    	} else if(Robot.m_oi.manip.getYButtonPressed() && Robot.m_oi.manip.getRBButtonPressed()) {
    		armToScale2();
    	} 
    }
    
    public boolean isPIDButtonPressed() {
    	return Robot.m_oi.manip.getAButtonPressed() || Robot.m_oi.manip.getBButtonPressed() || Robot.m_oi.manip.getYButtonPressed();
    }
    
    public void teleopArm() {
    	if(isPIDButtonPressed()) {
    		setArmPID();
    	} else {
    		manualArmMovement();
    	}
    }
}

