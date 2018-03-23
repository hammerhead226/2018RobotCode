package org.usfirst.frc.team226.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grp_ToggleAutonRecording extends CommandGroup {

    public grp_ToggleAutonRecording() {
    	addParallel(new ToggleProfileRecording());
    	addSequential(new ToggleActionListRecording());
    }
}
