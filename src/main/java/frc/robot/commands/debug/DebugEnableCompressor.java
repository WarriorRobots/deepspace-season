package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugEnableCompressor extends InstantCommand {

    public DebugEnableCompressor() {
        requires(Robot.pneumatics);
    }

    @Override
    protected void execute() {
        Robot.pneumatics.enableCompressor();
        DriverStation.reportWarning("WWDEBUG compressor enabled", false);
    }

}