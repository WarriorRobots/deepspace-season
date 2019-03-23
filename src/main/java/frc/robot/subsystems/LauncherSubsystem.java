/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class LauncherSubsystem extends Subsystem {

	private static final int LAUNCH_FORWARD = 4;
	private static final int LAUNCH_REVERSE = 3;
  
  private DoubleSolenoid launcherSol;

  public LauncherSubsystem() {

		launcherSol = new DoubleSolenoid(Constants.PCM_2, LAUNCH_FORWARD, LAUNCH_REVERSE);

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
	 * Shuts off power to the launcher solenoid(s).
	 * Use after extending or retracting; this will not move the piston.
	 */
	public void neutralizeLaunchers() {
		launcherSol.set(Value.kOff);
	}

  @Override
  public void initDefaultCommand() {
    //none
  }

  @Override
  public void initSendable(SendableBuilder builder) {
		builder.addStringProperty("launchers", () -> launcherSol.get().toString(), null);
  }
}
