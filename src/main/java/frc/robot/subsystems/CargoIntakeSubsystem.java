/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.cargo.DefaultIdleCargoPickupWheels;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class CargoIntakeSubsystem extends Subsystem {

    private static final int INTAKE_WHEELS_PORT = 1;

    private WPI_VictorSPX intakeWheels;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public CargoIntakeSubsystem() {
        intakeWheels = new WPI_VictorSPX(INTAKE_WHEELS_PORT);
        intakeWheels.setInverted(true);
    }

    /**
     * Run the motor that holds cargo balls.
     * 
     * @param speed Decimal value from -1 to 1.
     */
    public void runIntake(double speed) {
        intakeWheels.set(speed);
    }

    /**
     * Shuts off the pickup motor.
     */
    public void stopIntake() {
        intakeWheels.stopMotor();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultIdleCargoPickupWheels());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("cargointake-subsystem");
        builder.addDoubleProperty("cargointake wheel speed", () -> intakeWheels.get(), null);
    }

}
