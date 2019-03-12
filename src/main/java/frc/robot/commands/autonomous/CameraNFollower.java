package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.ApproachCurve;
import frc.robot.commands.autonomous.LineFollowCommand;

@Deprecated
public class CameraNFollower extends CommandGroup {
  /**
   * Command group to Cammera follow and then pass it over to the line follower.
   */
  public CameraNFollower() {
    addSequential(new ApproachCurve());
    addSequential(new LineFollowCommand());
  }
}
