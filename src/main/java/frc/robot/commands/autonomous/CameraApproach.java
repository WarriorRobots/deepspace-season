/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.util.SynchronousPIDF;

public class CameraApproach extends Command {

  private SynchronousPIDF PIDcenter;

  private double valueCenter;
  private double valueApproach;

  private Timer timer;

  public CameraApproach() {
    requires(Robot.drivetrain);
    requires(Robot.camera);

    valueApproach = 0;
    valueCenter = 0;

    PIDcenter = new SynchronousPIDF(QuickAccessVars.KP_CENTER, QuickAccessVars.KI_CENTER,
      QuickAccessVars.KD_CENTER);

    timer = new Timer();
  }

  @Override
  protected void initialize() {
    System.out.println("Camera: Starting " + this.getClass().getSimpleName());
    Robot.camera.setPipeline(CameraSubsystem.PIPELINE_CENTER);

    PIDcenter.setSetpoint(0); // keep the target in the center of the screen

    timer.start();
  }

  @Override
  protected void execute() {
    if (Robot.camera.canSeeObject()) {
      valueCenter = PIDcenter.calculate(Robot.camera.getObjectX(), timer.get());
    } else {
      valueCenter = 0;
    }

    // valueApproach = Robot.input.getRightY();
    valueApproach = ( Robot.input.getLeftY() + Robot.input.getRightY() ) / 2;

    Robot.drivetrain.arcadeDriveRaw(valueApproach, -valueCenter);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    System.out.println("Camera: Ending " + this.getClass().getSimpleName());
    timer.stop();
		PIDcenter.reset();
		valueApproach = 0;
		valueCenter = 0;
		Robot.drivetrain.stopDrive();
  }
}
