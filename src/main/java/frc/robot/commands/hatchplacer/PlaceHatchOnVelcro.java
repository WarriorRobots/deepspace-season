package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PlaceHatchOnVelcro extends CommandGroup {

    public PlaceHatchOnVelcro() {
        addSequential(new LoosenScissors());
        addSequential(new WaitCommand(0.5));
        addSequential(new ExtendLaunchers());
        addSequential(new WaitCommand(0.3));
        addSequential(new RetractLaunchers());
    }
    
}