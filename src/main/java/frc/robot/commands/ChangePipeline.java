/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** ChangePipeline changes the pipeline of the limeight to a new specified pipeline. */
public class ChangePipeline extends Command {

  int pipeline;

  public ChangePipeline(int pipeline) {
    requires(Robot.camera);
    this.pipeline = pipeline;
  }

  @Override
  protected void initialize() {
    Robot.camera.setPipeline(pipeline);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
