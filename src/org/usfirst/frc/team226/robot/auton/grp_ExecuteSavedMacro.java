package org.usfirst.frc.team226.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grp_ExecuteSavedMacro extends CommandGroup {

    public grp_ExecuteSavedMacro(String name) {
    	addParallel(new ExecuteSavedProfile(name));
    	addSequential(new ExecuteSavedActionList(name));
    }
    
    public grp_ExecuteSavedMacro() {
    	addParallel(new ExecuteSavedProfile());
    	addSequential(new ExecuteSavedActionList());
    }
}
