/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControlHandler;
import frc.robot.Robot;

public class LinearClimb extends Command {

  /**
   * Run the climb linearly based on the input from the Left joystick on the xbox controller
   * (as long as the elevator linear isn't being run)
   */
  public LinearClimb() {}
  
  @Override
  protected void initialize() {
    requires(Robot.climb);
  }

  @Override
  protected void execute() {
    if(!Robot.input.getXboxL3()) {
      Robot.climb.adjustClimbLinear(Robot.input.getXboxLeftY());
    }
    // XXX Do not use the XBox controller as linear input
    // Other things use the XBox joysticks for input
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
