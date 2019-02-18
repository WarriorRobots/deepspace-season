/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/** Launch a hatch off of the hatch placer */
public class HatchLaunch extends InstantCommand {
  
  public HatchLaunch() {
    super();
    requires(Robot.hatchplacer);
  }

  @Override
  protected void initialize() {
    Robot.hatchplacer.releaseHatch();
    Robot.hatchplacer.extendLauncher();
  }

}
