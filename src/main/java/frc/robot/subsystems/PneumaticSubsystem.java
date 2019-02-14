package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

@Deprecated
public class PneumaticSubsystem extends Subsystem {

    private DoubleSolenoid solenoid1, solenoid2, solenoid3, solenoid4;
    private Compressor c;

    public PneumaticSubsystem() {
        solenoid1 = new DoubleSolenoid(0, 7); 
        solenoid2 = new DoubleSolenoid(1, 6); 
        solenoid3 = new DoubleSolenoid(2, 5); 
        solenoid4 = new DoubleSolenoid(1, 3, 4);
        c = new Compressor();
        c.stop();
    }

    public void setSolenoid(int id, Value mode) {
        switch(id) {
            case 1: solenoid1.set(mode);
                break;
            case 2: solenoid2.set(mode);
                break;
            case 3: solenoid3.set(mode);
                break;
            case 4: solenoid4.set(mode);
                break;
            default: DriverStation.reportError("Wrong solenoid id", false);
                break;
        }
    }

    //TODO
    @Override
    protected void initDefaultCommand() {
    
    }

}
