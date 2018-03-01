package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.RunArm;

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
    	left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ARM_PIDSLOT_IDX, Constants.ARM_SENSOR_TIMEOUT);
    	
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
    	
    	left.configForwardSoftLimitThreshold(Constants.ARM_FORWARD_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	left.configReverseSoftLimitThreshold(Constants.ARM_REVERSE_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	right.configForwardSoftLimitThreshold(Constants.ARM_FORWARD_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	right.configReverseSoftLimitThreshold(Constants.ARM_REVERSE_LIMIT, Constants.ARM_SENSOR_TIMEOUT);
    	
    	left.configForwardSoftLimitEnable(Constants.ARM_FORWARD_LIMIT_ENABLED, Constants.ARM_SENSOR_TIMEOUT);
    	left.configReverseSoftLimitEnable(Constants.ARM_REVERSE_LIMIT_ENABLED, Constants.ARM_SENSOR_TIMEOUT);
    	right.configForwardSoftLimitEnable(Constants.ARM_FORWARD_LIMIT_ENABLED, Constants.ARM_SENSOR_TIMEOUT);
    	right.configReverseSoftLimitEnable(Constants.ARM_REVERSE_LIMIT_ENABLED, Constants.ARM_SENSOR_TIMEOUT);
    	
    	
    	right.follow(left);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new RunArm());
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
    	return left.getSelectedSensorPosition(Constants.ARM_PIDSLOT_IDX);
    }
    
    public void armHoldPos() {
    	left.set(ControlMode.MotionMagic, getArmPos());
    }
    
    public void manualArmMovement() {
    	if(Math.abs(Robot.oi.manip.getLeftJoystick_Y()) > 0) {
    		left.set(ControlMode.PercentOutput, Robot.oi.manip.getLeftJoystick_Y());
    	} else {
    		armHoldPos();
    	}
    }
   
    public void setArmPID() {
    	if(Robot.oi.manip.getAButtonPressed()) {
    		armToGround1();
    	} else if(Robot.oi.manip.getBButtonPressed()) {
    		armToSwitch1();
    	} else if(Robot.oi.manip.getYButtonPressed()) {
    		armToScale1();
    	} else if(Robot.oi.manip.getAButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToGround2();
    	} else if(Robot.oi.manip.getBButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToSwitch2();
    	} else if(Robot.oi.manip.getYButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToScale2();
    	} 
    }
    
    public boolean isPIDButtonPressed() {
    	return Robot.oi.manip.getAButtonPressed() || Robot.oi.manip.getBButtonPressed() || Robot.oi.manip.getYButtonPressed();
    }
    
    public void runArm() {
    	if(isPIDButtonPressed()) {
    		setArmPID();
    	} else {
    		manualArmMovement();
    	}
    }
}

