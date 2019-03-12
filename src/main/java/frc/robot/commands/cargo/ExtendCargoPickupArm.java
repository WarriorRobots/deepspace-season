/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendCargoPickupArm extends Command {

  private double angle;

  /**
   * Drops the arm to the specified angle.
   * 
   * @param angle Between 0 and 180 degrees
   */
  public ExtendCargoPickupArm(double angle) {
    requires(Robot.arm);
    this.angle = angle;
  }

  @Override
  protected void execute() {
    Robot.arm.rotateArmTo(angle);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.arm.stopArm();
  }

}
