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
import frc.robot.commands.cargo.BallIn;
import frc.robot.commands.cargo.BallOut;
import frc.robot.commands.drive.ArcadeDrive;
import frc.robot.commands.drive.TurnLockDrive;
import frc.robot.commands.elevator.MoveElevatorTo;
import frc.robot.commands.hatch.RetractPickup;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.hatch.DisableCompressor;
import frc.robot.commands.hatch.EnableCompressor;
import frc.robot.commands.hatch.ExtendPickup;
import frc.robot.commands.hatch.PlaceHatchGroup;
import frc.robot.commands.hatch.PullHatchIn;
import frc.robot.commands.hatch.ResetLauncher;
import frc.robot.commands.hatch.LetHatchOut;
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
	private JoystickButton leftJoyButton3, leftJoyButton4, leftJoyButton5;
	private JoystickButton rightJoyButton3, rightJoyButton4, rightJoyButton6, rightJoyButton7;
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper;
	private JoystickButton xboxX, xboxY, xboxB, xboxA, xboxSTART, xboxBACK;
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
		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		rightJoyButton4 = new JoystickButton(rightJoy, 4);
		rightJoyButton6 = new JoystickButton(rightJoy, 6);
		rightJoyButton7 = new JoystickButton(rightJoy, 7);

		leftXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kLeft), 0.5);
		rightXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kRight), 0.5);
		leftXboxBumper = new JoystickButton(xbox, 5);
		rightXboxBumper = new JoystickButton(xbox, 6);
		xboxUp = new DpadTrigger( () -> xbox.getPOV(), 0);
		xboxDown = new DpadTrigger( () -> xbox.getPOV(), 180);
		xboxRight = new DpadTrigger( () -> xbox.getPOV(), 90);
		xboxLeft = new DpadTrigger(() -> xbox.getPOV(), 270);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxSTART = new JoystickButton(xbox, 8);
		xboxBACK = new JoystickButton(xbox, 7);
		xboxLeftJoyUp = new ThresholdJoystick( () -> xbox.getY(Hand.kLeft), 0.75, ThresholdJoystick.UP);
		xboxLeftJoyDown = new ThresholdJoystick( () -> xbox.getY(Hand.kLeft), -0.75, ThresholdJoystick.DOWN);
		xboxRightJoyUp = new ThresholdJoystick( () -> xbox.getY(Hand.kRight), 0.75, ThresholdJoystick.UP);
		xboxRightJoyDown = new ThresholdJoystick( () -> xbox.getY(Hand.kLeft), -0.75, ThresholdJoystick.DOWN);

		// TODO
		// elevator reset, low, middle, high
		// ball rotate flat, vertical, diagonal

		rightJoyThumbButton.whileHeld(new ArcadeDrive());
		rightJoyTriggerButton.whileHeld(new TurnLockDrive());
		//leftJoyTriggerButton.whileHeld(new ApproachCurve());

		// right joystick
		rightJoyButton3.whenPressed(new MoveElevatorTo(5000)); // ball low
		rightJoyButton4.whenPressed(new BallOut());

		// left joystick
		leftJoyButton3.whenPressed(new ExtendPickup());
		leftJoyButton4.whenPressed(new PlaceHatchGroup());

		// hatch
		xboxA.whenPressed(new MoveElevatorTo(5000)); // low
		xboxB.whenPressed(new MoveElevatorTo(13000)); // mid
		xboxY.whenPressed(new MoveElevatorTo(23000)); // high
		rightXboxTrigger.whileHeld(new ResetLauncher()); // hold
		xboxRightJoyUp.whileHeld(new ExtendPickup());
		xboxRightJoyDown.whileHeld(new RetractPickup());

		// ball
		// low 5000
		xboxX.whenPressed(new MoveElevatorTo(15000)); // mid
		xboxSTART.whenPressed(new MoveElevatorTo(25000)); // high
		xboxLeftJoyUp(new cargoLevel());
		xboxLeftJoyDown(new cargoUp());
	}

	/**
	 * Gets Y-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getLeftY(double scalingFactor) {
		return leftJoy.getY() * scalingFactor;
	}
	
	/**
	 * Gets Y-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getRightY(double scalingFactor) {
		return rightJoy.getY() * scalingFactor;
	}

	/**
	 * Gets Y-value of left joystick.
	 */
	public double getLeftY() {
		return this.getLeftY(1);
	}

	/**
	 * Gets Y-value of right joystick.
	 */
	public double getRightY() {
		return this.getRightY(1);
	}
	
	/**
	 * Gets Y-value of left Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */
	public double getXboxLeftY(double scalingFactor) {
		double value = -xbox.getY(Hand.kLeft);
		return value * scalingFactor;
	}

	public double getXboxLeftX(double scalingFactor) {
		double value = xbox.getX(Hand.kLeft);
		return value * scalingFactor;
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */	
	public double getXboxRightY(double scalingFactor) {
		double value = -xbox.getY(Hand.kRight);
		return value * scalingFactor;
	}

	public double getXboxRightX(double scalingFactor) {
		double value = xbox.getX(Hand.kRight);
		return value * scalingFactor;
	}

	/**
	 * Gets Y-value of left Xbox joystick.
	 */	
	public double getXboxLeftY() {
		return this.getXboxLeftY(1);
	}

	public double getXboxLeftX() {
		return this.getXboxLeftX(1);
	}

	/**
	 * Gets Y-value of right Xbox joystick.
	 * @return Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY() {
		return this.getXboxRightY(1);
	}

	public double getXboxRightX() {
		return this.getXboxRightX(1);
	}
	
	/**
	 * Gets Y-value of left Xbox trigger.
	 * @return Y-value of left Xbox trigger.
	 */
	public double getXboxLeftTrigger() {
		return xbox.getTriggerAxis(Hand.kLeft);
	}
	
	/**
	 * Gets Y-value of right Xbox trigger.
	 * @return Y-value of right Xbox trigger.
	 */
	public double getXboxRightTrigger() {
		return xbox.getTriggerAxis(Hand.kRight);
	}
	
	public double getDpadAngle() {
		return xbox.getPOV();
	}
	
	/**
	 * Gets X-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getLeftX(double scalingFactor) {
		return leftJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getRightX(double scalingFactor) {
		return rightJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of left joystick.
	 */
	public double getLeftX() {
		return this.getLeftX(1);
	}

	/**
	 * Gets X-value of right joystick.
	 */
	public double getRightX() {
		return this.getRightX(1);
	}
	
}