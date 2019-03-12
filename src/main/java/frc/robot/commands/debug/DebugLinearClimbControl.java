/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearClimbControl extends Command {

  private DoubleSupplier input;

  /**
   * Run the climb linearly based on the input from the Left joystick on the xbox
   * controller (as long as the elevator linear isn't being run)
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
