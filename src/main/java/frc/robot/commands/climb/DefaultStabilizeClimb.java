package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeClimb extends Command {

    private double initialPosition;

    public DefaultStabilizeClimb() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        initialPosition = Robot.climb.getClimbPosition();
    }

    @Override
    protected void execute() {
        Robot.climb.stabilizeClimb(initialPosition);
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