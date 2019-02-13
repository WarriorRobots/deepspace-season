/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and out.
 */
public class CargoSubsystem extends Subsystem {

  private static final int PICKUP_PORT = 1;
  private static final int ROTATOR_PORT = 8;
  private static final int PID_ID = 0; // ID number of the PID profile
  private static final int TIMEOUT_MS = 10; // How long a Talon waits for signals before cancelling commands.

  private WPI_VictorSPX pickup;
  private WPI_TalonSRX rotator;

  /**
   * Instantiates new subsystem; make ONLY ONE.
   * <p><code>
   * public static final CargoSubsystem cargo = new CargoSubsystem();
   */
  public CargoSubsystem() {
    pickup = new WPI_VictorSPX(PICKUP_PORT);
    pickup.setNeutralMode(NeutralMode.Brake);
    
    rotator = new WPI_TalonSRX(ROTATOR_PORT);
    rotator.setNeutralMode(NeutralMode.Brake);

    rotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rotator.config_kP(PID_ID, 0, TIMEOUT_MS);
    rotator.config_kI(PID_ID, 0, TIMEOUT_MS);
    rotator.config_kD(PID_ID, 0, TIMEOUT_MS);
  }

  /**
   * Run the motor that holds cargo balls.
   * @param speed Decimal value from -1 to 1.
   */
  public void runPickupMotor(double speed) {
    pickup.set(speed);
  }

  /**
   * Rotate the pickup assembly to the specified angle in degrees.
   * @param positionDegrees Intended position in degrees (TODO specify range).
   */
  public void rotatePickupDegrees(double positionDegrees) {
    rotator.set(ControlMode.Position, positionDegrees); //TODO degrees and ticks
  }

  /**
   * Returns the angular position, in [degrees or ticks], of the pickup assembly.
   */
  public int getPickupPosition() {
    return rotator.getSelectedSensorPosition(); //TODO degrees conversion
  }

  /**
   * Shuts off the pickup motor.
   */
  public void stopPickup() {
    pickup.stopMotor();
  }

  /**
   * <b>Do not use except in emergencies!
   * </b>Shuts off the rotator motor.
   * <p><i>Warning:</i> Leaving the pickup in a half-rotated position could cause damage.
   */
  @Deprecated
  public void stopRotator() {
    rotator.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
    //TODO run pickup command? possibly joystick, or leave blank
  }

}
