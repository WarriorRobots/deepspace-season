package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.ChangeLEDPattern;

/**
 * LedController sends PWM signals to the REV Blinkin to change the color of LEDs
 */
public class LedControllerSubsystem extends Subsystem {

  private static final int LED_CONTROLLER_ID = 0;

  /** LED controller acts as a Spark motor controller for it's inputs for changing patterns. */
  private Spark LED_controller;

  
  // all available patterns are at http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf#page=14

  /**
   * Spark value for color of the LED strip when there is no other command.
   * <p>
   * 43 Fixed Pallete Pattern Breath, Blue
   */
  public static final double IDLE = -0.15;
  
  /**
   * Spark value for pattern for when the robot sees the target (in approach distance)
   * <p>
   * 45 Fixed Pallete Pattern Strobe, Red
   */
  public static final double seeTarget = -0.11;

  /**
   * Spark value for pattern for when the robot is at the target (and is ready to place)
   * <p>
   * 48 Fixed Pallete Pattern Strobe, White
   */
  public static final double atTarget = -0.05;

  // 94 Solid Colors Blue
  //public static final double solidblue = 0.87;
  


  public LedControllerSubsystem() {
    LED_controller = new Spark(LED_CONTROLLER_ID);
  }

  /**
   * Change the pattern of the LED Controller by using preset channel number in {@link LedControllerSubsystem}.
   * @param channel id as a spark value
   */
  public void setChannel(double channel) {
    LED_controller.set(channel);
  }

  /**
   * Gets the spark value that the LED Controller is currently using
   * @return spark value of current pattern
   */
  public double getChannel() {
    return LED_controller.get();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChangeLEDPattern(IDLE));
  }

  @Override
	public void initSendable(SendableBuilder builder) {
    // builder.addDoubleProperty("channel", () -> getChannel(), null);
  }
}
