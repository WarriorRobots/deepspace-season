/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.paths.AutoStraightDistance;
import frc.robot.commands.elevator.HomeElevator;
import frc.robot.commands.hatchplacer.LockScissors;

public class Rocket extends CommandGroup {
  public Rocket(String autoname) {
    addParallel(new RocketElevator()); // at the same time move the elevator up after 2 seconds
    addSequential(new RocketDrive(autoname)); // drive to the target and turn the correct direction
    addSequential(new RocketPlace()); // drive with camera, place, and backup
    addSequential(new AutoStraightDistance(-26));
    addSequential(new LockScissors());
    addSequential(new HomeElevator(),0.75); // bring elevator back down
  }

  @Override
  public void initialize() {
    // debug for drivers to know when the auto is running
    SmartDashboard.putBoolean("Auto Running", true);
  }

  @Override
  public void end() {
    // debug for drivers to know when the auto is stopped
    SmartDashboard.putBoolean("Auto Running", false);
  }

  @Override
  public void interrupted() {
    end();
  }
}
