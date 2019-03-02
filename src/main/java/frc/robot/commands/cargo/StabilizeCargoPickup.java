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
public class StabilizeCargoPickup extends Command {

  private double initialPosition;

  public StabilizeCargoPickup() {
    requires(Robot.arm);
  }

  @Override
  protected void initialize() {
    initialPosition = Robot.arm.getPickupPosition();
  }

  @Override
  protected void execute() {
    Robot.arm.rotatePickupTo(initialPosition);
    Robot.arm.runPickupMotor(0.1);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
  
  @Override
  protected void end() {
    Robot.arm.stopPickup();
  }
}
