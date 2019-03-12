package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunHatchPickupWheels extends Command {

  /**
   * Runs the hatch pickup wheels forwards, pulling a hatch into the robot. This
   * is only safe to run if the pneumatics are down.
   */
  public RunHatchPickupWheels() {
    requires(Robot.hatchPickupWheels);
  }

  @Override
  protected void execute() {
    Robot.hatchPickupWheels.runPickup(1);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hatchPickupWheels.stopPickup();
  }

}
