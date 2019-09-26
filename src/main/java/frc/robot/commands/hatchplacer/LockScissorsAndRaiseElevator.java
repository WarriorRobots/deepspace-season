/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.QuickAccessVars;
import frc.robot.commands.elevator.RaiseElevatorTo;

public class LockScissorsAndRaiseElevator extends CommandGroup {
  /**
   * Locks the scissors, then raises the elevator so that a disc will clear the
   * pickup mechanism or loading station brushes.
   */
  public LockScissorsAndRaiseElevator() {
    addSequential(new LockScissors());
    addSequential(new RaiseElevatorTo(QuickAccessVars.LOCK_AND_RAISE_HEIGHT));
    // if this command is interrupted it should be fine because pistons do not require constant signals
    // and the elevator has the stabilize command as it's default so it is unnessecary to check that case
  }

  @Override
  public void initialize() {
    System.out.println("Elevator/Pneumatics: Starting " + this.getClass().getSimpleName());
  }

  @Override
  public void end() {
    System.out.println("Elevator/Pneumatics: Finishing " + this.getClass().getSimpleName());
  }

  @Override
  public void interrupted() {
    System.out.println("Elevator/Pneumatics: Canceling " + this.getClass().getSimpleName());
  }
}
