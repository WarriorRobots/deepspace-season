package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;

public class GroupPlaceHatchOnVelcro extends CommandGroup {

    public GroupPlaceHatchOnVelcro(boolean safemode) {
        addParallel(new SubgroupExtendLaunchers(safemode));
        addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
        addParallel(new LoosenScissors());
        addSequential(new WaitCommand(0.3));
        addSequential(new SubgroupRetractLaunchers());
    }
    
}