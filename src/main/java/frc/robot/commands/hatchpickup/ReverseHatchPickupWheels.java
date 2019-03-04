/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseHatchPickupWheels extends Command {
  public ReverseHatchPickupWheels() {
    requires(Robot.hatchPickup);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatchPickup.runIntakeMotor(-0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hatchPickup.stopIntakeMotor();
  }

}
