/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoPickupSubsystem;
import frc.robot.util.annotations.Incomplete;

/** Put the cargo pickup into the Up position */
public class cargoUp extends Command {

  /** Angle of the cargo pick */
  //private double position;

  public cargoUp() {
    requires(Robot.cargoPickup);
  }

  @Override
  protected void execute() {
    Robot.cargoPickup.setPickupPosition(CargoPickupSubsystem.positionUp);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.cargoPickup.setPickupPosition(Robot.cargoPickup.getPickupPosition());
  }
}
