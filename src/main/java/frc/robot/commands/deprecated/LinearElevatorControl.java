package frc.robot.commands.deprecated;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinearElevatorControl extends Command {
    
    private DoubleSupplier input;

    public LinearElevatorControl(DoubleSupplier input) {
        requires(Robot.elevator);
        this.input = input;
    }

    @Override
    protected void execute() {
        Robot.elevator.adjustElevatorLinear(input.getAsDouble());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}