package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineFollowCommand extends Command {

  /** Value of how much to turn right on the left of the line */
  private static final double turn_constant = 0.12;

  private final static int LEFT = 0;
  private final static int MIDDLE = 1;
  private final static int RIGHT = 2;

  private int last_turn = MIDDLE;

  public LineFollowCommand() {
    requires(Robot.lineFollowers);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  /**
   * STEPS:
   * 1. Make sure that program only searches for line, does not start following until Approach sends "stop"
   * 2. Send value to Network table value of 'on-line' when infrared goes off
   * 3. In Approach Curve, go until on-line says TRUE and once stopped, make sure you tell line follow it's okay to start
   */
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double turn_speed;
    if(Robot.lineFollowers.onCenter()){
      turn_speed = 0;
      last_turn = MIDDLE;
    }else if(Robot.lineFollowers.onLeftOfLine()){
      turn_speed = turn_constant;
      last_turn = RIGHT;
    }else if(Robot.lineFollowers.onRightOfLine()){
      turn_speed = -turn_constant;
      last_turn = LEFT;
    }else{
      turn_speed = (last_turn == RIGHT)? -turn_constant:turn_constant;
      /** This is to correct when the robot moves away from the line */
      /** We are going to move in the opposite direction of the "last_turn" */
    }
    Robot.drivetrain.arcadeDriveRaw(0.25, turn_speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
