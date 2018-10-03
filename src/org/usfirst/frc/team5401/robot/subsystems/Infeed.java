package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeederControl;

/**
 *
 */
public class Infeed extends Subsystem {
	private DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	private double FEED_SPEED;
	
	private AnalogInput pressureSensor;
	private double inputVoltage;
	private final static double DEFAULT_VOLTS = 5.0;
	private final int SLOPE = 250;
	private final int Y_INTERCEPT = -20;
	
	public Infeed(){
		feederArm = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
		FEED_SPEED = 0.9;
		pressureSensor = new AnalogInput(RobotMap.PRESSURE_SENSOR);
		inputVoltage = DEFAULT_VOLTS;
		
		SmartDashboard.putString("FeederArm_text", "Feeder Arm");
		SmartDashboard.putString("FeederOut_text", "GREEN = Rollers Out");
		SmartDashboard.putString("FeederIn_text", "RED = Rollers In");
		if ((DoubleSolenoid.Value.kForward).equals(feederArm.get())){
		SmartDashboard.putNumber("Feeder Arm", -1);
	}
	else {
		SmartDashboard.putNumber("Feeder Arm", 1);
	}
		
		SmartDashboard.putString("FeederRollers_text", "Feeder Rollers");
		SmartDashboard.putString("FeederRollersOut_text", "GREEN = Rollers Out");
		SmartDashboard.putString("FeederRollersIn_text", "RED = Rollers In");
		SmartDashboard.putNumber("Feeder Rollers", 0);
		SmartDashboard.putNumber("Pressure", SLOPE * (pressureSensor.getVoltage()/ inputVoltage) + Y_INTERCEPT);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new FeederControl());
    }
    
    public void feederDirection(int direction){
    	feederMotor.set(FEED_SPEED * direction);
    	SmartDashboard.putNumber("Feeder Rollers", direction);
    }
    
    public void armUpDown(int direction){
    	if (direction == -1){
    	feederArm.set(DoubleSolenoid.Value.kForward);
    	SmartDashboard.putNumber("Feeder Arm", -1);
    }
    else if (direction == 1){
    	feederArm.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putNumber("Feeder Arm", 1);
    }
    	
    }
    
    public void reportPressure(){
    	SmartDashboard.putNumber("Pressure", SLOPE * (pressureSensor.getVoltage()/ inputVoltage + Y_INTERCEPT));
    }
   
}


