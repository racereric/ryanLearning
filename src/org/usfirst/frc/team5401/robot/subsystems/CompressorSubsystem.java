package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {
	private Compressor compressor;
	
	public CompressorSubsystem(){
		compressor = new Compressor(RobotMap.PCM_ID);
		SmartDashboard.putBoolean("Compressor On/Off", true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCompressor(){
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    	SmartDashboard.putBoolean("Compressor On/Off", true);
    }
    
    public void stopCompressor(){
    	compressor.stop();
    	SmartDashboard.putBoolean("Compressor On/Off", false);
    }
    
    public void getCompressorStatus(){
    	SmartDashboard.putBoolean("Compressor Enabled", compressor.enabled());
    	SmartDashboard.putBoolean("Compressor in Closed Loop", compressor.getClosedLoopControl());
    	SmartDashboard.putNumber("Compressor Current Value", compressor.getCompressorCurrent());
    	SmartDashboard.putBoolean("Compressor Switch On/Off ", compressor.getPressureSwitchValue());
    }
    
    public boolean isEnabled(){
    	return compressor.enabled();
    }
    
    public void switchState(){
    	if (isEnabled()){
    		stopCompressor();
    }
    else {
    	startCompressor();
    }
    	
    }
}

