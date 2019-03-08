package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class GroupRetractHatchPickup extends CommandGroup {

    public GroupRetractHatchPickup() {
        addParallel(new DefaultStopHatchPickupWheels());
        addParallel(new LoosenScissors());
        addParallel(new SubgroupRetractHatchPickup());
        addSequential(new WaitCommand(QuickAccessVars.HATCH_PICKUP_DELAY));
    }

}