package frc.robot.util.triggers;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Utility class that turns analog joystick inputs into boolean outputs.
 * <p>
 * Allows use of whenPressed(), whileHeld(), etc, which normally require
 * true/false inputs.
 */
public class ThresholdJoystick extends Button {

	private DoubleSupplier input;

	/*
	 * {@code true} for x; {@code false} for y
	 * @see #AXIS_X
	 * @see #AXIS_Y
	*/
	// private boolean axis;
	private double threshold;
	/**
	 * Set to show what direction the joystick has to be pushed past the threshold to return true.
	 * @see #LEFT
	 * @see #RIGHT
	 * @see #UP
	 * @see #DOWN
	 */
	private boolean direction;

	// public static final boolean AXIS_X = true;
	// public static final boolean AXIS_Y = false;

	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;
	public static final boolean UP = true;
	public static final boolean DOWN = false;
	
	/*
	 * @param axis		{@code true} if threshold is measured on x direction and {@code false}
	 * 					if measured on y.
	 */

	/**
	 * Usage: <code>new ThresholdTrigger( () -> input(), threshold, direction)</code>
	 * 
	 * @param input     Any joystick-related function that returns a <b>double</b>
	 *                  value.
	 * @param threshold The value to compare against for joystick.
	 * @param direction The direction the joystick has to be pushed past the threshold to trigger.
	 * @see #direction
	 */
	public ThresholdJoystick(DoubleSupplier input, double threshold, boolean direction) {
		this.input = input;
		this.threshold = threshold;
		this.direction = direction;
	}

	@Override
	public boolean get() {
		if(
			direction && input.getAsDouble() > threshold //(value should be greater than threshold) && value > treshold
			||
			!direction && input.getAsDouble() < threshold //(value should NOT be greater than threshold) && value < treshold
		) {
			return true;
		} else {
		return false;
		}
	}
}
