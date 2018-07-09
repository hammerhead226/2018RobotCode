package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

	private TalonSRX left = new TalonSRX(RobotMap.WRIST_LEFT);
	private TalonSRX right = new TalonSRX(RobotMap.WRIST_RIGHT);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Wrist() {
		left.configContinuousCurrentLimit(Constants.WRIST_CURRENT_LIMIT, Constants.WRIST_TIMEOUT);
		right.configContinuousCurrentLimit(Constants.WRIST_CURRENT_LIMIT, Constants.WRIST_TIMEOUT);
		
		left.enableCurrentLimit(Constants.WRIST_CURRENT_LIMIT_ENABLED);
		right.enableCurrentLimit(Constants.WRIST_CURRENT_LIMIT_ENABLED);

		left.configVoltageCompSaturation(Constants.WRIST_VOLTAGE_LIMIT, Constants.WRIST_TIMEOUT);
		right.configVoltageCompSaturation(Constants.WRIST_VOLTAGE_LIMIT, Constants.WRIST_TIMEOUT);

		left.enableVoltageCompensation(Constants.WRIST_VOLTAGE_LIMIT_ENABLED);
		right.enableVoltageCompensation(Constants.WRIST_VOLTAGE_LIMIT_ENABLED);

		left.setInverted(Constants.WRIST_INVERT_L);
		right.setInverted(Constants.WRIST_INVERT_R);
		
		right.follow(left);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveWrist(double speed) {
    	left.set(ControlMode.PercentOutput, speed);
    }
    
    public void neutralOutput() {
    	left.neutralOutput();
    }
}

