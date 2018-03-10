package org.usfirst.frc.team226.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grp_ExecuteDoubleMacro extends CommandGroup {

	public grp_ExecuteDoubleMacro(String leftName, String rightName) {
		addParallel(new ExecuteDoubleProfile(leftName, rightName));
		addSequential(new ExecuteDoubleActionList(leftName, rightName));
	}
}
