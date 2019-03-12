package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class LockScissors extends Command {

  private int counter;

  /**
   * Apply outward pressure with the "scissor" mechanism, grasping securely onto a
   * hatch if one is present.
   * <p>
   * This also gets the scissors out of the way of the arm mechanism if it is
   * being lowered.
   */
  public LockScissors() {
    requires(Robot.pneumatics);
  }

  @Override
  protected void initialize() {
    counter = 0;
  }

  @Override
  protected void execute() {
    Robot.pneumatics.lockScissors();
    counter++;
  }

  @Override
  protected boolean isFinished() {
    return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
  }

  @Override
  protected void end() {
    Robot.pneumatics.neutralizeScissors();
  }

}
