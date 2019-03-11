package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeClimb extends Command {

    /**
     * Keeps the climb at a consistant height so if it starts to go down it gets picked up.
     */
    public DefaultStabilizeClimb() {
        requires(Robot.climb);
    }

    @Override
    protected void execute() {
        Robot.climb.moveClimbTo(-0.3); // FIXME quickaccess
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.climb.stopClimb();
    }

}