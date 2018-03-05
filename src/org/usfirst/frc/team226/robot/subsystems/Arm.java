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
    	//left.set(ControlMode.MotionMagic, Constants.GROUND_FRONT);
    	heightHolder = Constants.GROUND_FRONT;    	
    }
    
    public void armToGroundBack() {
    	//left.set(ControlMode.MotionMagic, Constants.GROUND_BACK);
    	heightHolder = Constants.GROUND_BACK;
    }
    
    public void armToSwitchFront() {
    	//left.set(ControlMode.MotionMagic, Constants.SWITCH_FRONT);
    	heightHolder = Constants.SWITCH_FRONT;
    }
    
    public void armToSwitchBack() {
    	//left.set(ControlMode.MotionMagic, Constants.SWITCH_BACK);
    	heightHolder = Constants.SWITCH_BACK;
    }
    
    public void armToScaleFront() {
    	//left.set(ControlMode.MotionMagic, Constants.SCALE_FRONT);
    	heightHolder = Constants.SCALE_FRONT;
    }
    
    public void armToScaleBack() {
    	//left.set(ControlMode.MotionMagic, Constants.SCALE_BACK);
    	heightHolder = Constants.SCALE_BACK;
    }
    
    public int getArmPos() {
    	return left.getSelectedSensorPosition(Constants.ARM_PIDSLOT_IDX);
    }
    
    public void armHoldPos() {
    	left.set(ControlMode.MotionMagic, getArmPos());
    }
    
    
    public void hardZeroEncoder() {
    	left.setSelectedSensorPosition(0, Constants.ARM_PIDSLOT_IDX, Constants.ARM_SENSOR_TIMEOUT);
    	heightHolder = 0;
    }
    
    public void manualArmMovement() {
    	if(Math.abs(Robot.oi.manip.getLeftJoystick_Y()) > 0) {
    		left.set(ControlMode.PercentOutput, (Constants.ARM_JOYSTICK_COEFF * Robot.oi.manip.getLeftJoystick_Y()));
    		heightHolder = getArmPos();
    	} else {
    		setArmPos(heightHolder);
    		//left.set(ControlMode.PercentOutput, 0.1 );
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
    	if(Robot.oi.manip.getAButtonPressed() && (Robot.oi.manip.getRBButtonPressed() )) {
    		armToGroundBack();
    		System.out.println("groundback");
    	} else if(Robot.oi.manip.getBButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToSwitchBack();
    		System.out.println("switchback");
    	} else if(Robot.oi.manip.getYButtonPressed() && Robot.oi.manip.getRBButtonPressed()) {
    		armToScaleBack();
    		System.out.println("scaleback");
    	} else if(Robot.oi.manip.getAButtonPressed()) {
    		armToGroundFront();
    		System.out.println("groundfront");
    	} else if(Robot.oi.manip.getBButtonPressed()) {
    		armToSwitchFront();
    		System.out.println("switchfront");
    	} else if(Robot.oi.manip.getYButtonPressed()) {
    		armToScaleFront();
    		System.out.println("scalefront");
    	}
    }
    
    public boolean isPIDButtonPressed() {
    	return Robot.oi.manip.getAButtonPressed() || Robot.oi.manip.getBButtonPressed() || Robot.oi.manip.getYButtonPressed();
    }
    
    public void setArmPos(int position) {
    	left.set(ControlMode.MotionMagic, position);
    }
    
    public void runArm() {
    	if(!isPIDButtonPressed()) {
    		manualArmMovement();
    	} else {
    		setArmPID();
    		setArmPos(heightHolder);
    		System.out.println(heightHolder);
    	}
    	
    	
    }

	public int getArmError() {
		return left.getClosedLoopError(Constants.ARM_PIDSLOT_IDX);
	}
}

