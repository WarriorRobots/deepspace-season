package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearClimbControl extends Command {

  private DoubleSupplier input;

  /**
   * Given a lambda function reading a joystick, this command 
   * will drive the arm motor at a percentage speed.
   * <p> Safety is built in to avoid crashing the arm.
   * @param input A lambda function <code>() -> getSomeValue()</code> that returns
   *              a number between -1 (upwards) and 1 (outwards).
   */
  public DebugLinearClimbControl(DoubleSupplier input) {
    requires(Robot.climb);
    this.input = input;
  }

  @Override
  protected void execute() {
    Robot.climb.adjustClimbLinear(input.getAsDouble());
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
