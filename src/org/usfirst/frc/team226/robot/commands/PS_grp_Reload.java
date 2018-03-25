package org.usfirst.frc.team226.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PS_grp_Reload extends CommandGroup {

    public PS_grp_Reload() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new PS_ShooterForward());
    	addSequential(new Wait(1.2));
    	addSequential(new PS_PancakeForward());
    	addSequential(new Wait(0.5));
    	addSequential(new PS_ShooterReverse());
    }
}
