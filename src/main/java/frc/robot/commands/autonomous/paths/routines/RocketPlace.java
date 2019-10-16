/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.QuickAccessVars;
import frc.robot.commands.autonomous.CameraStopAtDistance;
import frc.robot.commands.hatchplacer.PlaceHatchOnVelcro;

public class RocketPlace extends CommandGroup {
  /**
   * Used to drive forwards towards the target and place
   * @see Rocket
   */
  public RocketPlace() {
    addSequential(new CameraStopAtDistance(true),1.5); // if it overshoots and doesn't backup, it should just shoot
    addSequential(new PlaceHatchOnVelcro(QuickAccessVars.HATCH_LAUNCH_SAFETY));
  }
}
