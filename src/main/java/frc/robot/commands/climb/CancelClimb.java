package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CancelClimb extends CommandGroup {

  public CancelClimb() {
    addParallel(new MoveClimbTo(0));
  }

}