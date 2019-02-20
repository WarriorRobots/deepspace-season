package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PlaceHatchGroup extends CommandGroup {

    public PlaceHatchGroup() {
        addSequential(new ReleaseHatch());
        addSequential(new WaitCommand(0.2));
        addSequential(new PushHatchOff());
        addSequential(new WaitCommand(0.5));
        addSequential(new ResetLauncher());
    }
    
}