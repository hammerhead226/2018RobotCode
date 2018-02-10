package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

//aUTh0R = vAruN rAj35h
public class Elevator extends Subsystem {
	
	TalonSRX frontLeft = new TalonSRX(RobotMap.EL_FRONT_LEFT);
    TalonSRX frontRight = new TalonSRX(RobotMap.EL_FRONT_RIGHT);
    TalonSRX rearLeft = new TalonSRX(RobotMap.EL_REAR_LEFT);
    TalonSRX rearRight = new TalonSRX(RobotMap.EL_REAR_RIGHT);
    
    DigitalInput hallEffect = new DigitalInput(RobotMap.HALL_EFFECT_SENSOR);
    
    double intakeHeight;
    double switchHeight;
    double powHeight;
    double scaleHeight;
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Elevator() {
    	frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	
    	frontLeft.configVoltageCompSaturation(11.5, 0);
    	frontRight.configVoltageCompSaturation(11.5, 0);
    	rearLeft.configVoltageCompSaturation(11.5, 0);
    	rearRight.configVoltageCompSaturation(11.5, 0);
    	
    	frontLeft.enableVoltageCompensation(true);
    	frontRight.enableVoltageCompensation(true);
    	rearLeft.enableVoltageCompensation(true);
    	rearRight.enableVoltageCompensation(true);
    	
    	rearLeft.setInverted(true);
    	rearRight.setInverted(true);
   
    	frontRight.follow(frontLeft);
        rearLeft.follow(frontLeft);
        rearRight.follow(frontLeft);
    }
    public void setElevator(int height) {
    	switch(height) {
    	case 0:
    		frontLeft.set(ControlMode.MotionMagic, intakeHeight);
    		break;
    	case 1:
    		frontLeft.set(ControlMode.MotionMagic, switchHeight);
    		break;
    	case 2:
    		frontLeft.set(ControlMode.MotionMagic, powHeight);
    		break;
    	case 3:
    		frontLeft.set(ControlMode.MotionMagic, scaleHeight);
    		break;
    	}
    	
    }
    public void zero() {
    	if(hallEffect.get()) {
    		frontLeft.setSelectedSensorPosition(0, 0, 0);
    	}
    }
}

