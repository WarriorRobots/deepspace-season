package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugResetArmEncoder extends InstantCommand {

    /**
     * Sets the current angle of the arm as the new "0 degrees". Use with care, as
     * this bypasses the hall effect sensor.
     */
    public DebugResetArmEncoder() {
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        Robot.arm.resetArmAngle();
        DriverStation.reportWarning("WWDEBUG: resetting encoder", false);
    }

}