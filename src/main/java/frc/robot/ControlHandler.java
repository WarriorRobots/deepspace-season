/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.cargo.RunCargoPickupWheels;
import frc.robot.commands.debug.DebugLinearArmControl;
import frc.robot.commands.debug.DebugLinearElevatorControl;
import frc.robot.commands.debug.DebugResetCargoPickupEncoder;
import frc.robot.commands.cargo.ReverseCargoPickupWheels;
import frc.robot.commands.debug.DebugDisableCompressor;
import frc.robot.commands.debug.DebugEnableCompressor;
import frc.robot.commands.debug.DebugResetAll;
import frc.robot.commands.cargo.ExtendCargoPickupArm;
import frc.robot.commands.cargo.RetractCargoPickupArm;
import frc.robot.commands.drive.ArcadeDrive;
import frc.robot.commands.drive.TurnLockDrive;
import frc.robot.commands.elevator.MoveElevatorTo;
import frc.robot.commands.elevator.DropElevator;
import frc.robot.commands.hatchpickup.RetractHatchPickup;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.hatchpickup.ExtendHatchPickup;
import frc.robot.commands.hatchpickup.RunHatchPickupWheels;
import frc.robot.commands.hatchplacer.LockScissors;
import frc.robot.commands.hatchplacer.LoosenScissors;
import frc.robot.commands.hatchplacer.GroupPlaceHatchOnVelcro;
import frc.robot.commands.hatchplacer.SubgroupRetractLaunchers;
import frc.robot.commands.hatchpickup.ReverseHatchPickupWheels;
import frc.robot.util.triggers.DpadTrigger;
import frc.robot.util.triggers.ThresholdJoystick;
import frc.robot.util.triggers.ThresholdTrigger;

/**
 * Contains methods for receiving data from Joysticks and the Xbox controller.
 */
@SuppressWarnings("unused")
public final class ControlHandler {

	private static final int LEFT_JOY = 1;
	private static final int RIGHT_JOY = 0;
	private static final int XBOX = 2;

	private Joystick leftJoy, rightJoy;
	private XboxController xbox;

	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton, leftJoyTriggerButton;
	private JoystickButton leftJoyButton3, leftJoyButton4, leftJoyButton5, leftJoyButton6, leftJoyButton7,
			leftJoyButton8, leftJoyButton9, leftJoyButton10;
	private JoystickButton rightJoyButton3, rightJoyButton4, rightJoyButton5, rightJoyButton6;
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper, xboxL3, xboxR3;
	private JoystickButton xboxX, xboxY, xboxB, xboxA, xboxSTART, xboxSELECT;
	private DpadTrigger xboxUp, xboxDown, xboxLeft, xboxRight;
	private ThresholdJoystick xboxLeftJoyUp, xboxLeftJoyDown, xboxRightJoyUp, xboxRightJoyDown;

	public ControlHandler() {
		leftJoy = new Joystick(LEFT_JOY);
		rightJoy = new Joystick(RIGHT_JOY);
		xbox = new XboxController(XBOX);

		leftJoyTriggerButton = new JoystickButton(leftJoy, 1);
		leftJoyButton3 = new JoystickButton(leftJoy, 3);
		leftJoyButton4 = new JoystickButton(leftJoy, 4);
		leftJoyButton5 = new JoystickButton(leftJoy, 5);
		leftJoyButton6 = new JoystickButton(leftJoy, 6);
		leftJoyButton7 = new JoystickButton(leftJoy, 7);
		leftJoyButton8 = new JoystickButton(leftJoy, 8);
		leftJoyButton9 = new JoystickButton(leftJoy, 9);
		leftJoyButton10 = new JoystickButton(leftJoy, 10);

		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		rightJoyButton4 = new JoystickButton(rightJoy, 4);
		rightJoyButton5 = new JoystickButton(rightJoy, 5);
		rightJoyButton6 = new JoystickButton(rightJoy, 6);

		leftXboxTrigger = new ThresholdTrigger(() -> xbox.getTriggerAxis(Hand.kLeft), 0.5);
		rightXboxTrigger = new ThresholdTrigger(() -> xbox.getTriggerAxis(Hand.kRight), 0.5);
		leftXboxBumper = new JoystickButton(xbox, 5);
		rightXboxBumper = new JoystickButton(xbox, 6);
		xboxUp = new DpadTrigger(() -> xbox.getPOV(), 0);
		xboxDown = new DpadTrigger(() -> xbox.getPOV(), 180);
		xboxRight = new DpadTrigger(() -> xbox.getPOV(), 90);
		xboxLeft = new DpadTrigger(() -> xbox.getPOV(), 270);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxSTART = new JoystickButton(xbox, 8);
		xboxSELECT = new JoystickButton(xbox, 7);
		xboxLeftJoyUp = new ThresholdJoystick(() -> xbox.getY(Hand.kLeft), 0.3, ThresholdJoystick.UP);
		xboxLeftJoyDown = new ThresholdJoystick(() -> xbox.getY(Hand.kLeft), -0.3, ThresholdJoystick.DOWN);
		xboxRightJoyUp = new ThresholdJoystick(() -> xbox.getY(Hand.kRight), 0.3, ThresholdJoystick.UP);
		xboxRightJoyDown = new ThresholdJoystick(() -> xbox.getY(Hand.kRight), -0.3, ThresholdJoystick.DOWN);
		xboxL3 = new JoystickButton(xbox, 9);
		xboxR3 = new JoystickButton(xbox, 10);

		// buttons on base of left joystick (hard to reach, debug only)
		leftJoyButton7.whenPressed(new DebugResetAll());
		leftJoyButton8.whenPressed(new DebugEnableCompressor());
		leftJoyButton10.whenPressed(new DebugDisableCompressor());

		// right joystick
		rightJoyThumbButton.whileHeld(new ArcadeDrive());
		rightJoyTriggerButton.whileHeld(new TurnLockDrive());
		rightJoyButton3.whenPressed(new ExtendCargoPickupArm()); // ball low
		rightJoyButton3.whenPressed(new LockScissors());
		// rightJoyButton4.whileHeld(new CameraCommand()); // unwritten

		// left joystick
		leftJoyButton4.whenPressed(new GroupPlaceHatchOnVelcro(QuickAccessVars.HATCH_LAUNCH_SAFETY));
		leftJoyTriggerButton.whenPressed(new DropElevator());

		// hatch
		xboxA.whenPressed(new MoveElevatorTo(QuickAccessVars.LVL1_HEIGHT));
		xboxB.whenPressed(new MoveElevatorTo(QuickAccessVars.LVL2_HEIGHT));
		xboxY.whenPressed(new MoveElevatorTo(QuickAccessVars.LVL3_HEIGHT));
		xboxX.whenPressed(new RetractHatchPickup());
		xboxSTART.whenPressed(new ExtendHatchPickup());
		xboxSTART.whenPressed(new LoosenScissors());
		xboxSELECT.whenPressed(new RetractCargoPickupArm());
		rightXboxTrigger.whenPressed(new RunHatchPickupWheels());
		rightXboxBumper.whenPressed(new ReverseHatchPickupWheels());
		leftXboxTrigger.whileHeld(new RunCargoPickupWheels());
		leftXboxBumper.whileHeld(new ReverseCargoPickupWheels());
		xboxLeft.whenPressed(new LoosenScissors());
		xboxRight.whenPressed(new LockScissors());

		// unknowns
		leftXboxBumper.whenPressed(new RetractCargoPickupArm());
		leftJoyButton9.whenPressed(new DebugResetCargoPickupEncoder());
	}

	// -----------------------------------------------------------------//

	/**
	 * Gets Y-value of left joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getLeftY(double scalingFactor) {
		return leftJoy.getY() * scalingFactor * -1;
	}

	/**
	 * Gets Y-value of left joystick.
	 */
	public double getLeftY() {
		return this.getLeftY(1);
	}

	/**
	 * Gets Y-value of right joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getRightY(double scalingFactor) {
		return rightJoy.getY() * scalingFactor * -1;
	}

	/**
	 * Gets Y-value of right joystick.
	 */
	public double getRightY() {
		return this.getRightY(1);
	}

	// -----------------------------------------------------------------//

	/**
	 * Gets X-value of left joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getLeftX(double scalingFactor) {
		return leftJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of left joystick.
	 */
	public double getLeftX() {
		return this.getLeftX(1);
	}

	/**
	 * Gets X-value of right joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getRightX(double scalingFactor) {
		return rightJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of right joystick.
	 */
	public double getRightX() {
		return this.getRightX(1);
	}

	// -----------------------------------------------------------------//

	/**
	 * Gets Y-value of left Xbox joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getXboxLeftY(double scalingFactor) {
		return xbox.getY(Hand.kLeft) * scalingFactor * -1.0; // inverted so that up is positive
	}

	/**
	 * Gets Y-value of left Xbox joystick.
	 */
	public double getXboxLeftY() {
		return this.getXboxLeftY(1);
	}

	/**
	 * Gets X-value of left Xbox joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getXboxLeftX(double scalingFactor) {
		return xbox.getX(Hand.kLeft) * scalingFactor;
	}

	/**
	 * Gets X-value of left Xbox joystick.
	 */
	public double getXboxLeftX() {
		return this.getXboxLeftX(1);
	}

	// -----------------------------------------------------------------//

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getXboxRightY(double scalingFactor) {
		return xbox.getY(Hand.kRight) * scalingFactor * -1.0; // inverted so that up is positive
	}

	/**
	 * Gets Y-value of right Xbox joystick.
	 */
	public double getXboxRightY() {
		return this.getXboxRightY(1);
	}

	/**
	 * Gets X-value of right Xbox joystick multiplied by scalingFactor.
	 * 
	 * @param scalingFactor Decimal value that proportionally scales output.
	 */
	public double getXboxRightX(double scalingFactor) {
		return xbox.getX(Hand.kRight) * scalingFactor;
	}

	/**
	 * Gets X-value of right Xbox joystick.
	 */
	public double getXboxRightX() {
		return this.getXboxRightX(1);
	}

	// -----------------------------------------------------------------//

	/**
	 * Get the angle, in degrees, that the D-pad buttons are currently pressed in.
	 * <p>
	 * 0 degrees is up, and angle increases in a clockwise direction.
	 * <p>
	 * <b>WARNING:</b> If the Xbox is unplugged, the code thinks UP is being
	 * pressed!
	 */
	public double getDpadAngle() {
		return xbox.getPOV();
	}

}