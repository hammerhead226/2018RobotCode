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

    private TalonSRX left = new TalonSRX(RobotMap.ARM_LEFT_ID);
    private TalonSRX right = new TalonSRX(RobotMap.ARM_RIGHT_ID);
    int heightHolder = 0;
    
    public Arm() {
    	left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.ARM_PIDSLOT_IDX, Constants.ARM_SENSOR_TIMEOUT);
    	left.setSensorPhase(Constants.ARM_SENSOR_PHASE);
    	left.setSelectedSensorPosition(0, 0, 0);
    	
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
    
    public void armToGroundFront() {
    	left.set(ControlMode.MotionMagic, Constants.GROUND_FRONT);
    }
    
    public void armToGroundBack() {
    	left.set(ControlMode.MotionMagic, Constants.GROUND_BACK);
    }
    
    public void armToSwitchFront() {
    	left.set(ControlMode.MotionMagic, Constants.SWITCH_FRONT);
    }
    
    public void armToSwitchBack() {
    	left.set(ControlMode.MotionMagic, Constants.SWITCH_BACK);
    }
    
    public void armToScaleFront() {
    	left.set(ControlMode.MotionMagic, Constants.SCALE_FRONT);
    }
    
    public void armToScaleBack() {
    	left.set(ControlMode.MotionMagic, Constants.SCALE_BACK);
    }
    
    public int getArmPos() {
    	return left.getSelectedSensorPosition(Constants.ARM_PIDSLOT_IDX);
    }
    
    public void armHoldPos() {
    	left.set(ControlMode.MotionMagic, getArmPos());
    }
    
    public void manualArmMovement() {
    	if(Math.abs(Robot.oi.manip.getLeftJoystick_Y()) > 0) {
    		left.set(ControlMode.PercentOutput, limitingAngle(Robot.oi.manip.getLeftJoystick_Y()));
    		heightHolder = getArmPos();
    	} else {
    		setArmPosition(heightHolder);
    	}
    }
    
    public double limitingAngle(double joystick) {
    	if(getArmPos() >= Constants.ARM_FORWARD_LIMIT && joystick > 0) {
    		return 0.0;
    	} else if(getArmPos() <= Constants.ARM_REVERSE_LIMIT && joystick < 0) {
    		return 0.0;
    	} else {
    		return joystick;
    	}
    }
   
    public void setArmPID() {
    	if(Robot.oi.manip.getAButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToGroundBack();
    	} else if(Robot.oi.manip.getBButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToSwitchBack();
    	} else if(Robot.oi.manip.getYButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToScaleBack();
    	} else if(Robot.oi.manip.getAButtonPressed()) {
    		armToGroundFront();
    	} else if(Robot.oi.manip.getBButtonPressed()) {
    		armToSwitchFront();
    	} else if(Robot.oi.manip.getYButtonPressed()) {
    		armToScaleFront();
    	}
    }
    
    public boolean isPIDButtonPressed() {
    	return Robot.oi.manip.getAButtonPressed() || Robot.oi.manip.getBButtonPressed() || Robot.oi.manip.getYButtonPressed();
    }
    
    public void hardZeroEncoder() {
    	left.setSelectedSensorPosition(0, 0, 0);
    }
    
    public void setArmPosition(int position) {
    	left.set(ControlMode.MotionMagic, position);
    }
    
    public void runArm() {
    	if(!isPIDButtonPressed()) {
    		manualArmMovement();
    	} else {
    		setArmPID();
    	}
    }
}

