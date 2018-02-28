package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.TeleopCarriage;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

    TalonSRX left = new TalonSRX(RobotMap.CARRIAGE_LEFT_ID);
    TalonSRX right = new TalonSRX(RobotMap.CARRIAGE_RIGHT_ID);
    
    public Carriage() {
  		
    	left.configContinuousCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT, Constants.CARRIAGE_SENSOR_TIMEOUT);
    	right.configContinuousCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT, Constants.CARRIAGE_SENSOR_TIMEOUT);
    	 		
    	left.enableCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT_ENABLED);	
    	right.enableCurrentLimit(Constants.CARRIAGE_CURRENT_LIMIT_ENABLED);
    	
    	left.configVoltageCompSaturation(Constants.CARRIAGE_VOLTAGE_LIMIT, Constants.CARRIAGE_SENSOR_TIMEOUT);
    	right.configVoltageCompSaturation(Constants.CARRIAGE_VOLTAGE_LIMIT, Constants.CARRIAGE_SENSOR_TIMEOUT);
    	
    	left.enableVoltageCompensation(Constants.CARRIAGE_VOLTAGE_LIMIT_ENABLED);
    	right.enableVoltageCompensation(Constants.CARRIAGE_VOLTAGE_LIMIT_ENABLED);
    	
    	left.setInverted(Constants.CARRIAGE_INVERT_L);
    	right.setInverted(Constants.CARRIAGE_INVERT_R);

    }

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopCarriage());
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
    
    public void carriageIntake() {
    	left.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
    	right.set(ControlMode.PercentOutput, Constants.CARRIAGE_INTAKE_SPEED);
    }
    
    public void carriageNeutral() {
    	left.neutralOutput();
    	right.neutralOutput();
    }
    
    public void teleopCarriage() {
    	if(Robot.isArmGround) {
    		setCarriageSpeed(Robot.oi.driver.getLeftJoystick_Y());
    	} else {
    		carriageNeutral();
    	}
    }
}

