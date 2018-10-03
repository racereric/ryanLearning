package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Climber extends Subsystem {
	private VictorSP climberMotor;
	
	private double SPEED;
	
	public Climber(){
		climberMotor = new VictorSP(RobotMap.CLIMBER_MOTOR);
		SPEED = 0.9;
		SmartDashboard.putNumber("ClimbSpeed", SPEED);
	}


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void climbUp(){
		climberMotor.set(SPEED);
	}
	
	public void climbStop(){
		climberMotor.set(0);
	}
}

