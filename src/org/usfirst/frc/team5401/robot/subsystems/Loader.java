package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Loader extends Subsystem {
	private VictorSP ConveyorMotor;
	private VictorSP MeteringMotor;
	
	private double LOADER_SPEED;
	private double METERING_SPEED;
	private boolean enabled;
	
	public Loader(){
		ConveyorMotor = new VictorSP(RobotMap.HOPPER_CONVEYOR);
		MeteringMotor = new VictorSP(RobotMap.METERING_ROLLER);
		
		LOADER_SPEED = -0.9;
		METERING_SPEED = -0.95;
		enabled = false;
		SmartDashboard.putBoolean("Loader Conveyors", enabled);
	}
	
	public void runConveyorsAndMeteringMotor(){
		enabled = true;
		MeteringMotor.set(METERING_SPEED);
		ConveyorMotor.set(LOADER_SPEED);
	}
	
	public void stopConveyorsAndMeteringMotor(){
		enabled = false;
		MeteringMotor.set(0);
		ConveyorMotor.set(0);
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void switchState(){
		if (!enabled){
		runConveyorsAndMeteringMotor();
	} else {
		stopConveyorsAndMeteringMotor();
	}
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

