package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugResetCargoPickupEncoder extends InstantCommand {

    public DebugResetCargoPickupEncoder() {
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        Robot.arm.resetArmAngle();
        DriverStation.reportWarning("WWDEBUG resetting encoder", false);
    }

}