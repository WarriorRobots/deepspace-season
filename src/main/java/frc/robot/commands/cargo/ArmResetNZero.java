/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.debug.DebugResetArmEncoder;

public class ArmResetNZero extends CommandGroup {
  /**
   * Used for bringing the arm to the hall effect and then to the zero.
   */
  public ArmResetNZero() {
    addSequential(new DebugResetArmEncoder());
    addSequential(new RetractCargoPickupArm());
  }
}
