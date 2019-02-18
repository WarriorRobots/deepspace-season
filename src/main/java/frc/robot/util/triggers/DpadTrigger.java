package frc.robot.util.triggers;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Utility class that converts D-pad inputs into true/false outputs.
 * <p>
 * Allows use of whenPressed(), whileHeld(), etc, which normally require
 * true/false inputs.
 */
public class DpadTrigger extends Button {

	private Supplier<Integer> input;
	private int angle;

	/**
	 * Usage: <code>new DpadTrigger( () -> input(), angle)</code>
	 * 
	 * @param input Any function that returns the angular direction of a D-pad
	 *              button press. Try <code>getPOV()</code>.
	 * @param angle The angle position required for the trigger to return true.
	 */
	public DpadTrigger(Supplier<Integer> input, int angle) {
		this.angle = angle;
		this.input = input;
	}

	@Override
	public boolean get() {
		return (input.get() == angle) ? true : false;
	}

}
