package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 *
 */
public class Shooter extends Subsystem {
	
	TalonSRX _talonMaster;
	TalonSRX _talonSlave;
	
	
	private double VOLTAGE_MOTOR_SPEED = .78;
	private double PID_MOTOR_SPEED = -19900;
	private double MOTOR_SPEED = PID_MOTOR_SPEED;
	private double feed_forward;
	private double kP, kI, kD;
	
	private int Izone;
	private double rampRate;
	private int channel;
	private boolean compressorEnabled;
	private boolean pidEnabled;
	private int THRESH;
	
	
	public Shooter(){
		_talonMaster = new TalonSRX(0);
		_talonSlave = new TalonSRX(1);
		
		_talonMaster.set(ControlMode.Velocity, 0);
		_talonSlave.set(ControlMode.Follower, _talonMaster.getDeviceID());
		
		SmartDashboard.putNumber("Motor_Speed", MOTOR_SPEED);
		SmartDashboard.putBoolean("AutoTargeting", false);
		
		_talonMaster.getSensorCollection().getQuadraturePosition();
		SmartDashboard.putNumber("Position", _talonMaster.getSensorCollection().getQuadraturePosition());
		_talonMaster.getSensorCollection().getQuadratureVelocity();
		SmartDashboard.putNumber("Velocity", _talonMaster.getSensorCollection().getQuadratureVelocity());
		SmartDashboard.putNumber("Velocity Raw", _talonMaster.getSensorCollection().getQuadratureVelocity());
		_talonMaster.getSensorCollection().setQuadraturePosition(0, 10);
		
		feed_forward = .033;
		
		kP = .1;
		kI = .000005;
		kD = 2;
		
		Izone = 0;
		rampRate = 10.23;
		channel = 0;
		
		pidEnabled = true;
		SmartDashboard.putBoolean("PID_Enabled", pidEnabled);
		SmartDashboard.putNumber("feed_forward", feed_forward);
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("kI", kI);
		SmartDashboard.putNumber("kD", kD);
		SmartDashboard.putNumber("rampRate", rampRate);
		
		_talonMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		THRESH = 200;
		reset();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startMotors(){
    	if(pidEnabled){
    	_talonMaster.config_kP(0, kP, 10);
    	_talonMaster.config_kI(0,  kI, 10);
    	_talonMaster.config_kD(0, kD, 10);
    	_talonMaster.config_kF(0, feed_forward, 10);
    	_talonMaster.config_IntegralZone(0, Izone, 10);
    	_talonMaster.set(ControlMode.Velocity, 0);
    	System.out.println("Mode: Velocity");
    } else {
    	_talonMaster.set(ControlMode.PercentOutput, MOTOR_SPEED);
    	System.out.println("Mode: PercentOutput");
    }
    	
    	SmartDashboard.putNumber("feed_forward_test", feed_forward);
    	
    	SmartDashboard.putNumber("Position", _talonMaster.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Velocity", _talonMaster.getSensorCollection().getQuadratureVelocity());
    	SmartDashboard.putNumber("Velocity_Raw", _talonMaster.getSensorCollection().getQuadratureVelocity());
    	
    	compressorEnabled = true;
    	
    }
    
    public void reset(){
    	stop();
    	SmartDashboard.putBoolean("AutoTargeting", false);
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	compressorEnabled = false;
    }
    
    public void stop(){
    	_talonMaster.set(ControlMode.PercentOutput, 0);
    	compressorEnabled = false;
    }
    
    public double getTargetSpeed(){
    	return MOTOR_SPEED; 
    }
    
    public double getVelocity(){
    	return _talonMaster.getSensorCollection().getQuadratureVelocity();
    }
    
    public boolean isEnabled(){
    	return compressorEnabled;
    }
    
    public void switchState(){
    	if(compressorEnabled){
    		reset();
    }
    else {
    		startMotors();
    }
    	
    }
    
    public void shootOverrideSwitchState(){
    	if(pidEnabled){
    		_talonMaster.set(ControlMode.PercentOutput, MOTOR_SPEED);
    		pidEnabled = false;
    		System.out.println("Switch to Voltage");
    }
    else {
    		_talonMaster.set(ControlMode.Velocity, MOTOR_SPEED);
    		pidEnabled = true;
    		System.out.println("Switch to Speed");
    }
    	SmartDashboard.putBoolean("PID_Enabled", pidEnabled);
    	
    }
    
    public void printReadyToShoot(){
    	if (_talonMaster.getSensorCollection().getQuadratureVelocity() < MOTOR_SPEED + THRESH  || _talonMaster.getSensorCollection().getQuadratureVelocity() > MOTOR_SPEED - THRESH){
    	SmartDashboard.putBoolean("Ready to Shoot", true);	
    }
    else {
    	SmartDashboard.putBoolean("Ready to Shoot", false);
    }
    	
    }
}

