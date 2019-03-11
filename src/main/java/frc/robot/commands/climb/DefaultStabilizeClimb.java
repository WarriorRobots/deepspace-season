package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeClimb extends Command {

    private double initialPosition;

    /**
     * Keeps the climb at a consistant height so if it starts to go down it gets picked up.
     */
    public DefaultStabilizeClimb() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        initialPosition = Robot.climb.getClimbPosition();
    }

    @Override
    protected void execute() {
        Robot.climb.moveClimbTo(initialPosition);
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