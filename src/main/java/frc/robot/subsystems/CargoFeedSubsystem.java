/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CargoFeedSubsystem extends Subsystem {

  public static final int TALON_FEED = 0;

  public static boolean feed_extended;

  public CargoFeedSubsystem() {
    feed_extended = false;
  }

  // extend feed out
  public void extend_feed() {
    // extend
    feed_extended = true;
  }

  // retract feed in
  public void retract_feed() {
    // retract
    feed_extended = false;
  }

  // return whether feed is out
  public boolean get_feed() {
    return feed_extended;
  }

  // run feed moters at a percent from -1 (in) to 1 (out)
  public void run_feed(double percent) {
    // run at percent
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
