package frc.robot.util.triggers;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Utility class that turns analog joystick/trigger inputs into boolean outputs.
 * <p>
 * Allows use of whenPressed(), whileHeld(), etc, which normally require
 * true/false inputs.
 */
public class ThresholdTrigger extends Button {

	private DoubleSupplier input;
	private double threshold;

	/**
	 * Usage: <code>new ThresholdTrigger( () -> input(), threshold)</code>
	 * 
	 * @param input     Any joystick-related function that returns a <b>double</b>
	 *                  value.
	 * @param threshold The minimum value required for the trigger to return true.
	 */
	public ThresholdTrigger(DoubleSupplier input, double threshold) {
		this.input = input;
		this.threshold = threshold;
	}

	@Override
	public boolean get() {
		return (Math.abs(input.getAsDouble()) > threshold) ? true : false;
	}

}
