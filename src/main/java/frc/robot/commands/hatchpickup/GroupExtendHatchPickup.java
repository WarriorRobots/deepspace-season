package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class GroupExtendHatchPickup extends CommandGroup {

    public GroupExtendHatchPickup() {
        addParallel(new LoosenScissors());
        addParallel(new SubgroupExtendHatchPickup());
        addSequential(new WaitCommand(0.3)); // XXX quickaccess this
        addSequential(new SubgroupRunHatchPickupWheels());
    }

}