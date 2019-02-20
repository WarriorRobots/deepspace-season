/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Sabilize the cargo pickup */
public class StabilizePickup extends Command {

  /** Initial angle of pickup */
  private double initial;

  public StabilizePickup() {
    requires(Robot.cargoPickup);
  }

  @Override
  protected void initialize() {
    initial = Robot.cargoPickup.getPickupPosition();
  }

  @Override
  protected void execute() {
    Robot.cargoPickup.setPickupPosition(initial);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
