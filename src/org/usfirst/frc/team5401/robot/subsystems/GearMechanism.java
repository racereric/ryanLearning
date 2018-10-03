package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.RobotMap;



/**
 *
 */
public class GearMechanism extends Subsystem {
	
	private DoubleSolenoid gearManipulator;
	
	
	public GearMechanism(){
		gearManipulator = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIPULATOR_IN, RobotMap.GEAR_MANIPULATOR_OUT);
		
		SmartDashboard.putString("GearMechanism_text", "Gear Mechanism");
		SmartDashboard.putString("GearOut_text", "Green = Gear Out");
		SmartDashboard.putString("GearIn_text", "Red = Gear In");
		
		if((DoubleSolenoid.Value.kForward).equals(gearManipulator.get())){
			SmartDashboard.putNumber("Gear Mechanism", 1);  //this means the gear mechanism is in
		} else {
			SmartDashboard.putNumber("Gear Mechanism", -1);  //this means the gear mechanism is out
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gearInOut(int direction){
    	if(direction == 1){
    		gearManipulator.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Gear Mechanism", 1);
    	} else if(direction == -1) {
    		gearManipulator.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Gear Mechanism", -1);
    	}
    }
}

