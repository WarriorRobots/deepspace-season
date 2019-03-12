package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SubgroupExtendLaunchers extends Command {

  private int counter;
  /** Set to true to prevent hatch shots unless within 1'6" of the velcro */
  private boolean safemode;

  /**
   * Subgroup command that extends the hatch launcher pistons. Do not use this; use the GroupCommand instead.
   * @param safemode If this is true, the launchers cannot fire without a signal from the line followers.
   */
  public SubgroupExtendLaunchers(boolean safemode) {
    requires(Robot.pneumatics);
    this.safemode = safemode;
  }

  @Override
  protected void initialize() {
    counter = 0;
  }

  @Override
  protected void execute() {
    if (safemode) {
      if (Robot.lineFollowers.getMiddleLineFollower()) {
        Robot.pneumatics.extendLaunchers();
      } //else do nothing
    } else {
      Robot.pneumatics.extendLaunchers();
    }
    counter++;
  }

  @Override
  protected boolean isFinished() {
    return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
  }

  @Override
  protected void end() {
    Robot.pneumatics.neutralizeLaunchers();
  }

}
