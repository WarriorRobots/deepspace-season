/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;
import frc.robot.commands.elevator.MoveElevatorTo;

public class RocketElevator extends CommandGroup {
  /**
   * Used to raise the elevator after 2 seconds of driving
   * @see Rocket
   */
  public RocketElevator() {
    addSequential(new WaitCommand(2));
    addSequential(new MoveElevatorTo(QuickAccessVars.LVL2_HEIGHT));
  }
}
