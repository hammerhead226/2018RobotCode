package org.usfirst.frc.team226.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grp_ExecuteSavedMacro extends CommandGroup {

    public grp_ExecuteSavedMacro(String profilename, String actionlistname) {
    	addParallel(new ExecuteSavedProfile(profilename));
    	addSequential(new ExecuteSavedActionList(actionlistname));
    }
    
    public grp_ExecuteSavedMacro() {
    	addParallel(new ExecuteSavedProfile());
    	addSequential(new ExecuteSavedActionList());
    }
}
