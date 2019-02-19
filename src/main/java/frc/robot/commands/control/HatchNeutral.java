/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/** Set hatch placer back into a neutral position, used after launching */
public class HatchNeutral extends InstantCommand {
  
  public HatchNeutral() {
    super();
    requires(Robot.hatchPlacer);
  }

  @Override
  protected void initialize() {
    Robot.hatchPlacer.secureHatch();
    Robot.hatchPlacer.retractLauncher();
  }

}
