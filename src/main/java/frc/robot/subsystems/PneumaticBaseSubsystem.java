package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

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

}
