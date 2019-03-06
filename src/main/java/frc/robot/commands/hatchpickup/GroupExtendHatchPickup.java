package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class GroupExtendHatchPickup extends CommandGroup {

    public GroupExtendHatchPickup() {
        addParallel(new LoosenScissors());
        addParallel(new SubgroupExtendHatchPickup());
        addSequential(new WaitCommand(QuickAccessVars.HATCH_PICKUP_DELAY));
        addSequential(new RunHatchPickupWheels());
    }

}