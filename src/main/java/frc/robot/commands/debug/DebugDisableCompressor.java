package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugDisableCompressor extends InstantCommand {

    public DebugDisableCompressor() {
        requires(Robot.pneumatics);
    }

    @Override
    protected void execute() {
        Robot.pneumatics.disableCompressor();
        DriverStation.reportWarning("WWDEBUG compressor disabled", false);
    }

}