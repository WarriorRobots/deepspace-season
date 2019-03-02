package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Contains the base pneumatic components (only the compressor, for now).
 */
public class PneumaticBaseSubsystem extends Subsystem {

    private Compressor compressor;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final PneumaticSubsystem pneumaticBase = new
     * PneumaticSubsystem();
     */
    public PneumaticBaseSubsystem() {
        compressor = new Compressor();
        compressor.start();
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
        // TODO current limiting?
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("compressor-data");
        builder.addBooleanProperty("low pressure?", () -> compressor.getPressureSwitchValue(), null);
        builder.addBooleanProperty("compressor running?", () -> compressor.enabled(), null);
    }

}
