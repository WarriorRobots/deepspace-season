package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ChangeLEDPattern extends Command {

  private double channel;

  /**
   * Changes the LEDs' pattern based on given channel value
   * @param channel id as a spark value
   */
  public ChangeLEDPattern(double channel) {
    requires(Robot.leds);
    
    this.channel = channel;
  }

  @Override
  protected void initialize() {
    Robot.leds.setChannel(channel);
    System.out.println("LED: Running " + this.getClass().getSimpleName());
  }

  @Override
	protected boolean isFinished() {
		return false;
	}
}
