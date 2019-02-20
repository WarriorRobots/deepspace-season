package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class EnableCompressor extends InstantCommand {

    public EnableCompressor() {
        requires(Robot.pneumaticBase);
    }

    @Override
    protected void execute() {
        Robot.pneumaticBase.enableCompressor();
    }

}