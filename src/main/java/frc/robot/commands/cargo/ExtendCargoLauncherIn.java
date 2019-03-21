/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatchplacer.SubgroupRetractLaunchers;
// remove reference to launcher
public class ExtendCargoLauncherIn extends CommandGroup { // FIXME break up name
  /**
   * Add your docs here.
   */
  public ExtendCargoLauncherIn(double angle) {
    addSequential(new SubgroupRetractLaunchers());
    addSequential(new ExtendCargoPickupArm(angle));
  }
}
