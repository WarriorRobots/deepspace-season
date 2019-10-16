/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.paths.AutoDrive;

public class Forwards extends CommandGroup {
  /**
   * Used for forwards path
   * @param autoname name of the forwards auto path
   */
  public Forwards(String autoname) {
    addSequential(new AutoDrive(autoname));
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
