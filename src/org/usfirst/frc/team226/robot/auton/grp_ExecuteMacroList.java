package org.usfirst.frc.team226.robot.auton;

import java.util.List;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grp_ExecuteMacroList extends CommandGroup {

	String voidMacro = "null";

	public grp_ExecuteMacroList(List<String> leftNames, List<String> rightNames) {
//		while(true) {
//			if(leftNames.size() < rightNames.size()) {
//				leftNames.add(voidMacro);
//			}
//			
//			if(rightNames.size() < leftNames.size()) {
//				rightNames.add(voidMacro);
//			}
//			
//			if(leftNames.size() == rightNames.size()) {
//				break;
//			}
//		}
		
		/*for (int i = 0; i < leftNames.size() || i < rightNames.size(); i++) {
			addSequential(new ExecuteChoiceMacro(leftNames.get(i), rightNames.get(i)));
			System.out.println(leftNames.get(i) + "   " + rightNames.get(i));
		}*/
		
		for (int i = 0; i < leftNames.size(); i++) {
			addSequential(new ExecuteChoiceMacro(leftNames.get(i), rightNames.get(i)));
		}
	}
}
