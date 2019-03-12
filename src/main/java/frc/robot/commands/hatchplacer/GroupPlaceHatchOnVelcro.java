package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;

public class GroupPlaceHatchOnVelcro extends CommandGroup {

    /**
     * Command to place a hatch on the velcro strips.
     * @param safemode If this is set to true, the hatch cannot be placed without a
     *                 signal from the line followers.
     */
    public GroupPlaceHatchOnVelcro(boolean safemode) {
        addParallel(new SubgroupExtendLaunchers(safemode));
        addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
        addParallel(new LoosenScissors());
        addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
        addSequential(new SubgroupRetractLaunchers());
    }

}