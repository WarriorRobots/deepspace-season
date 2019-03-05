package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GroupPlaceHatchOnVelcro extends CommandGroup {

    public GroupPlaceHatchOnVelcro(boolean safemode) {
        addParallel(new SubgroupExtendLaunchers(safemode));
        addSequential(new WaitCommand(0.2));
        addParallel(new LoosenScissors());
        addSequential(new WaitCommand(0.3));
        addSequential(new SubgroupRetractLaunchers());
    }
    
}