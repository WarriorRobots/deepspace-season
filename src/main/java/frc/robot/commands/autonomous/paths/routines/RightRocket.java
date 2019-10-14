/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.paths.AutoDrive;
import frc.robot.commands.autonomous.paths.AutoTurn;

@Deprecated
public class RightRocket extends CommandGroup {


  /**
   * Go behind the rocket and turn 90 degrees right
   */
  public RightRocket() {
    addSequential(new AutoDrive("TESTBackStraightRocket"));
    addSequential(new AutoTurn(90)); // 90 right
  }
}
