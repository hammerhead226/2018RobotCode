package org.usfirst.frc.team226.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSystem extends Subsystem {

	public Compressor compressor = new Compressor(12);
	
	public PneumaticsSystem() {
		compressor.start();
	}

    public void initDefaultCommand() {
    }
    
}

