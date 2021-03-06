/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatchplacer.LockScissors;
import frc.robot.commands.hatchplacer.SubgroupRetractLaunchers;

public class RetractArm extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RetractArm() {
    addParallel(new SubgroupRetractLaunchers());
    addParallel(new LockScissors());
    addParallel(new SubgroupRetractArm());
  }
}
