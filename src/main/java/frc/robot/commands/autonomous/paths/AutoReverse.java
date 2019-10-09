/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class AutoReverse extends Command {

  private String k_path_name;
  private EncoderFollower m_left_follower, m_right_follower;
  private Notifier m_follower_notifier;

  public AutoReverse(String path_name) {
    requires(Robot.drivetrain);

    k_path_name = path_name;
  }

  @Override
  protected void initialize() {
    // Create trajectories for each side to follow
    // (trajectories are instantiated inside the initialize because the WPI docs show it that way)
    Trajectory left_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".right");
    Trajectory right_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".left");
    // left and right trajectories are flipped to fix the issue listed on the WPI Docs
    // https://wpilib.screenstepslive.com/s/currentCS/m/84338/l/1021631-integrating-path-following-into-a-robot-program#known-issue

    // Create followerers that follow the trajectories above
    m_left_follower = new EncoderFollower(left_trajectory);
    m_right_follower = new EncoderFollower(right_trajectory);

    // Configure both followers to have the right encoder values and PIDVA values
    m_left_follower.configureEncoder(Robot.drivetrain.getLeftEncoderClicks(), DrivetrainSubsystem.CLICKS_PER_REV, DrivetrainSubsystem.WHEEL_DIAMETER);
    m_left_follower.configurePIDVA(QuickAccessVars.KP_LEFTAUTO, QuickAccessVars.KI_LEFTAUTO, QuickAccessVars.KD_LEFTAUTO, QuickAccessVars.KV_LEFTAUTO, QuickAccessVars.KA_LEFTAUTO);

    m_right_follower.configureEncoder(Robot.drivetrain.getRightEncoderClicks(), DrivetrainSubsystem.CLICKS_PER_REV, DrivetrainSubsystem.WHEEL_DIAMETER);
    m_right_follower.configurePIDVA(QuickAccessVars.KP_RIGHTAUTO, QuickAccessVars.KI_RIGHTAUTO, QuickAccessVars.KD_RIGHTAUTO, QuickAccessVars.KV_RIGHTAUTO, QuickAccessVars.KA_RIGHTAUTO);
    // the left and right encoders are opposite and flipped sides of each other for going reverse
    
    // zero gyro so that it thinks it starts facing 0 like as described by the CSV path
    Robot.drivetrain.resetAngle();
    
    // Create notifier to call followPath() to compute speeds and send them to the motor
    m_follower_notifier = new Notifier(this::followPath); // this::followPath tells it to call followPath
    m_follower_notifier.startPeriodic(left_trajectory.get(0).dt); // left_trajectory.get(0).dt is the Time Step from the Path Weaver Project
  }

  private void followPath() {
    if (isFinished()) {
      // When finished, stop the notifier (and it is also told to stop in end() in case the command stops)
      m_follower_notifier.stop();
    }
    // when it is still following the path (aka it isn't finished)
    else {
      // calculate desired output for motors
      double left_speed = -m_right_follower.calculate(Robot.drivetrain.getLeftEncoderClicks());
      double right_speed = -m_left_follower.calculate(Robot.drivetrain.getRightEncoderClicks());
      // the right path is the left side in reverse and the left path is the right side in reverse

      // calculate desired amount to turn for motors
      double heading = Robot.drivetrain.getAngleDegrees();
      double desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
      // the value has a negative sign in front to fix the issue listed on the WPI Docs
      // https://wpilib.screenstepslive.com/s/currentCS/m/84338/l/1021631-integrating-path-following-into-a-robot-program#known-issue
      // the value is negated again because the robot is going backwards
      SmartDashboard.putNumber("Desired Heading", desired_heading); // XXX test

      double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
      SmartDashboard.putNumber("Heading Difference", heading_difference); // XXX test
      double turn =  QuickAccessVars.KG_AUTO * heading_difference;
      SmartDashboard.putNumber("Auto motor Turn", turn); // XXX test

      // drive the motors using the desired outputs for the motors combined with the amount it should turn
      Robot.drivetrain.tankDriveRaw(left_speed + turn, right_speed - turn);
      // going in reverse means the motor values are the reverse of their own side
    }
  }

  // This function is deliberately left blank
  @Override
  protected void execute() {
    // This function is deliberately left blank as it mimics AutonomousPeriodic on the WPI docs
    // The motors are called by the m_follower_notifier so they do not need to be called by the excute
  }

  @Override
  protected boolean isFinished() {
    // When either of the two followers are finished, the auton is finished
    return (m_left_follower.isFinished() || m_right_follower.isFinished());
  }

  // Stop the notifier from calling the motors to run and stop the motors from running
  @Override
  protected void end() {
    m_follower_notifier.stop();
    Robot.drivetrain.stopDrive();
  }
}
