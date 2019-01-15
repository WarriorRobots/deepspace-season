package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.enums.SolenoidMode;

public class PneumaticSubsystem extends Subsystem {

    DoubleSolenoid solenoid1, solenoid2, solenoid3, solenoid4;

    public PneumaticSubsystem() {
        solenoid1 = new DoubleSolenoid(0, 7); 
        solenoid2 = new DoubleSolenoid(1, 6); 
        solenoid3 = new DoubleSolenoid(2, 5); 
        solenoid4 = new DoubleSolenoid(3, 4); 
    }

    public void setSolenoid(int id, Value mode) {
        if(id == 1) {
            solenoid1.set(mode);
        }else if(id == 2) {
            solenoid2.set(mode);
        }else if(id == 3) {
            solenoid3.set(mode);
        }else if(id == 4) {
            solenoid4.set(mode);
        }else{
            DriverStation.reportError("Wrong solenoid id", false);
        }
    }

    //TODO
    @Override
    protected void initDefaultCommand() {
        setSolenoid(0, Value.kForward);
    }

}
