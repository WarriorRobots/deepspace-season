package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExtendAndRunHatchPickup extends CommandGroup {

    public ExtendAndRunHatchPickup() {
        addSequential(new ExtendHatchPickup());
        addSequential(new WaitCommand(0.5));
        addSequential(new RunHatchPickupWheels());
    }

}