/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.paths.AutoCompleteTurn;
import frc.robot.commands.autonomous.paths.AutoDrive;

public class RocketDrive extends CommandGroup {
  /**
   * Used to drive to the rocket and face the propper direction
   * @see Rocket
   */
  public RocketDrive(String autoname) {
    addSequential(new AutoDrive(autoname));
    addSequential(new AutoCompleteTurn());
  }
}
