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
import frc.robot.commands.EmergencyResetAll;
import frc.robot.commands.climb.HookBackwards;
import frc.robot.commands.climb.HookForwards;
import frc.robot.commands.climb.WinchInwards;
import frc.robot.commands.climb.WinchOutwards;
import frc.robot.commands.drive.ArcadeDriveAlignment;
import frc.robot.commands.drive.CameraAlign;
import frc.robot.commands.drive.TankDriveTurnLock;
import frc.robot.commands.pneumatics.ClosePickup;
import frc.robot.commands.pneumatics.LowerHood;
import frc.robot.commands.pneumatics.OpenPickup;
import frc.robot.commands.pneumatics.RaiseAndClose;
import frc.robot.commands.pneumatics.RaiseHood;
import frc.robot.commands.scoring.PickupCubeFromPortal;
import frc.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.robot.commands.scoring.sequence.EjectCube;
import frc.robot.commands.scoring.sequence.PickupCube;
import frc.robot.commands.scoring.sequence.TopLoadCube;
import frc.robot.commands.scoring.shooter.ShootHigh;
import frc.robot.commands.scoring.shooter.ShootLow;
import frc.robot.commands.scoring.shooter.ShootMid;
import frc.robot.commands.scoring.shooter.ShootSuperHigh;
import frc.robot.commands.scoring.shooter.ShootSwitch;
import frc.robot.util.triggers.DpadTrigger;
import frc.robot.util.triggers.ThresholdTrigger;

/**
 * Contains methods for receiving data from Joysticks and the Xbox controller.
 */
public final class ControlHandler {

	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	private Joystick leftJoy, rightJoy;
	private XboxController xbox;

	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton, leftJoyTriggerButton;
	private JoystickButton leftJoyButton3, leftJoyButton4, leftJoyButton5;
	private JoystickButton rightJoyButton3, rightJoyButton4, rightJoyButton6, rightJoyButton7;
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper;
	private JoystickButton xboxX, xboxY, xboxB, xboxA, xboxSTART, xboxBACK;
	private DpadTrigger xboxUp, xboxDown, xboxLeft, xboxRight;
	
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
		
		leftJoyTriggerButton.whileHeld(new CameraAlign());
		leftJoyButton3.whileHeld(new TopLoadCube());
		leftJoyButton4.whenPressed(new ClosePickup());
		leftJoyButton5.whileHeld(new WinchOutwards());
		rightJoyTriggerButton.whileHeld(new TankDriveTurnLock());
		rightJoyThumbButton.whileHeld(new ArcadeDriveAlignment());
		rightJoyButton3.whenPressed(new OpenPickup());
		rightJoyButton4.whileHeld(new ShootSwitch());
		rightJoyButton4.whileHeld(new RunFeedAtDefault());
		rightJoyButton6.whileHeld(new WinchInwards());
		rightJoyButton7.whenPressed(new EmergencyResetAll());

		leftXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kLeft), 0.5);
		rightXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kRight), 0.5);
		leftXboxBumper = new JoystickButton(xbox, 5);
		rightXboxBumper = new JoystickButton(xbox, 6);
		xboxUp = new DpadTrigger( () -> xbox.getPOV(), 0);
		xboxDown = new DpadTrigger( () -> xbox.getPOV(), 180);
		xboxRight = new DpadTrigger( () -> xbox.getPOV(), 90);
		xboxLeft = new DpadTrigger( () -> xbox.getPOV(), 270);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxSTART = new JoystickButton(xbox, 8);
		xboxBACK = new JoystickButton(xbox, 7);

		leftXboxTrigger.whileHeld(new PickupCube());
		rightXboxTrigger.whileHeld(new ShootSwitch());
		leftXboxBumper.whileHeld(new EjectCube());
		rightXboxBumper.whileHeld(new PickupCubeFromPortal());
		xboxUp.whileHeld(new ShootHigh());
		xboxDown.whileHeld(new ShootLow());
		xboxRight.whileHeld(new ShootMid());
		xboxLeft.whileHeld(new ShootSuperHigh());
		xboxA.whileHeld(new RunFeedAtDefault());
		xboxB.whenPressed(new ClosePickup());
		xboxX.whenPressed(new LowerHood());
		xboxY.whenPressed(new RaiseAndClose());
		xboxSTART.whenPressed(new RaiseHood());
		xboxSTART.whileHeld(new HookForwards());
		xboxBACK.whileHeld(new HookBackwards());
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

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */	
	public double getXboxRightY(double scalingFactor) {
		double value = -xbox.getY(Hand.kRight);
		return value * scalingFactor;
	}

	/**
	 * Gets Y-value of left Xbox joystick.
	 */	
	public double getXboxLeftY() {
		return this.getXboxLeftY(1);
	}

	/**
	 * Gets Y-value of right Xbox joystick.
	 * @return Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY() {
		return this.getXboxRightY(1);
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