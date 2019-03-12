package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * Contains all pneumatics on the robot.
 */
public class PneumaticSubsystem extends Subsystem {

	private static final int SCISSOR_FORWARD = 5;
	private static final int SCISSOR_REVERSE = 2;
	private static final int LAUNCH_FORWARD = 4;
	private static final int LAUNCH_REVERSE = 3;
	private static final int PICKUP_FORWARD = 0;
	private static final int PICKUP_REVERSE = 7;

	private Compressor compressor;
	private DoubleSolenoid scissorSol, launcherSol, pickupSol;

	/**
	 * Instantiates new subsystem; make ONLY ONE.
	 * <p>
	 * <code> public static final HatchPlacerSubsystem hatchPlacer = new
	 * HatchPlacerSubsystem();
	 */
	public PneumaticSubsystem() {
		compressor = new Compressor(Constants.PCM_1);
		compressor.start();

		scissorSol = new DoubleSolenoid(Constants.PCM_2, SCISSOR_FORWARD, SCISSOR_REVERSE);
		launcherSol = new DoubleSolenoid(Constants.PCM_2, LAUNCH_FORWARD, LAUNCH_REVERSE);
		pickupSol = new DoubleSolenoid(Constants.PCM_1, PICKUP_FORWARD, PICKUP_REVERSE);
	}

	/**
	 * Secures the hatch in place by opening the scissors.
	 */
	public void lockScissors() {
		scissorSol.set(Value.kReverse);
	}

	/**
	 * Releases the hatch by closing the scissors; it will hang loosely and can fall off.
	 */
	public void loosenScissors() {
		scissorSol.set(Value.kForward);
	}

	/**
	 * Extend the pistons that push the hatch off the scissors.
	 * Only use if the scissors are loose!
	 */
	public void extendLaunchers() {
		launcherSol.set(Value.kForward);
	}

	/**
	 * Retract the pistons that push the hatch off the scissors.
	 * Always run this after launching to avoid unsafe conditions.
	 */
	public void retractLaunchers() {
		launcherSol.set(Value.kReverse);
	}

	/**
	 * Extends the solenoid that puts the pickup mechanism on the ground.
	 */
	public void extendPickup() {
		pickupSol.set(Value.kForward);
	}

	/**
	 * Pulls the pickup mechanism back up to the robot.
	 * Make sure the scissors are in place!
	 */
	public void retractPickup() {
		pickupSol.set(Value.kReverse);
	}

	/**
	 * Shuts off power to the launcher solenoid(s).
	 * Use after extending or retracting; this will not move the piston.
	 */
	public void neutralizeLaunchers() {
		launcherSol.set(Value.kOff);
	}

	/**
	 * Shuts off power to the scissors solenoid.
	 * Use after extending or retracting; this will not move the piston.
	 */
	public void neutralizeScissors() {
		scissorSol.set(Value.kOff);
	}

	/**
	 * Shuts off power to the pickup solenoid.
	 * Use after extending or retracting; this will not move the piston.
	 */
	public void neutralizePickup() {
		pickupSol.set(Value.kOff);
	}

	/**
	 * Shuts off power to all solenoids.
	 * Use after extending or retracting; this will not move any pistons.
	 */
	public void neutralizeAll() {
		neutralizeScissors();
		neutralizeLaunchers();
		neutralizePickup();
	}

	/**
	 * Allows the compressor to pump air at low pressures (not all the time).
	 */
	public void enableCompressor() {
		compressor.start();
	}

	/**
	 * Prevents the compressor from pumping air, at any time.
	 */
	public void disableCompressor() {
		compressor.stop();
	}

	@Override
	protected void initDefaultCommand() {
		// none
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addBooleanProperty("low pressure?", () -> !compressor.getPressureSwitchValue(), null);
		builder.addBooleanProperty("compressor?", () -> compressor.enabled(), null);
		builder.addStringProperty("scissors", () -> scissorSol.get().toString(), null);
		builder.addStringProperty("launchers", () -> launcherSol.get().toString(), null);
		builder.addStringProperty("hatchpickup", () -> pickupSol.get().toString(), null);
	}

}