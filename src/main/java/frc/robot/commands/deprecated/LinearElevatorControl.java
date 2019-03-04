package frc.robot.commands.deprecated;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinearElevatorControl extends Command {
    
    private DoubleSupplier input;

    public LinearElevatorControl(DoubleSupplier input) {
        requires(Robot.elevator);
        requires(Robot.drivetrain);//XXX remove this
        this.input = input;
    }

    @Override
    protected void execute() {
        if (!Robot.elevator.isElevatorFloored()) {
            Robot.elevator.adjustElevatorLinear(input.getAsDouble());
        } else {
            if (input.getAsDouble() < 0) {
                Robot.elevator.stopElevator();
            } else {
                Robot.elevator.adjustElevatorLinear(input.getAsDouble());
            }
        }
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