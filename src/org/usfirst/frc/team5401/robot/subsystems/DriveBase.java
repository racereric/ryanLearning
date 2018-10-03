package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Encoder;
import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class DriveBase extends Subsystem {
	
	double HIGH_GEAR_LEFT_DPP;
	double LOW_GEAR_LEFT_DPP;
	double HIGH_GEAR_RIGHT_DPP;
	double LOW_GEAR_RIGHT_DPP;
	
	private VictorSP leftDriveMotor1;
	private VictorSP rightDriveMotor1;
	private VictorSP leftDriveMotor2;
	private VictorSP rightDriveMotor2;
	
	private DoubleSolenoid gearShifter;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	AHRS navxGyro;
	
	public DriveBase() {
		HIGH_GEAR_LEFT_DPP  = -.019423;
		HIGH_GEAR_RIGHT_DPP =  .019423;
		
		LOW_GEAR_LEFT_DPP   = -.0189429;
		LOW_GEAR_RIGHT_DPP  =  .0189429;
		
		leftDriveMotor1  = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_1);
		rightDriveMotor1 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_1);
		leftDriveMotor2  = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_2);
		rightDriveMotor2 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_2);
		
	    gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.SHIFTER_IN, RobotMap.SHIFTER_OUT);
	    leftEncoder = new Encoder(RobotMap.LEFT_ENC_1, RobotMap.LEFT_ENC_2, true, Encoder.EncodingType.k4X);
	    rightEncoder = new Encoder(RobotMap.RIGHT_ENC_1, RobotMap.RIGHT_ENC_2, true, Encoder.EncodingType.k4X);
	    
	    navxGyro = new AHRS(SerialPort.Port.kMXP);
	    navxGyro.reset();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired) {
    	leftDriveMotor1.set(leftDriveDesired);
    	rightDriveMotor1.set(-1 * rightDriveDesired);
    	leftDriveMotor2.set(leftDriveDesired);
    	rightDriveMotor2.set(-1 * rightDriveDesired);
    	
    	SmartDashboard.putNumber("Left Encoder Raw", leftEncoder.get());
    	SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
    	SmartDashboard.putNumber("Left Enc Adj", leftEncoder.getDistance());
    	SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
    	SmartDashboard.putNumber("Mean Encoder Adj" , getEncoderDistance());
    }
    
    public void stopDriveBase() {
    	leftDriveMotor1.set(0);
    	rightDriveMotor1.set(0);
    	leftDriveMotor2.set(0);
    	rightDriveMotor2.set(0);
    }
    
    public void shiftGearHighToLow() {
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	leftEncoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", 1);
    	System.out.println("Shifted to Low Gear");
    }
    
    public void shiftGearLowToHigh() {
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	leftEncoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", -1);
    	System.out.println("Shifted to High Gear");
    }
    
    public void setDPPHighGear() {
    	leftEncoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    }
    
    public void setDPPLowGear() {
    	leftEncoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    }
    
    public double reportRobotVelocity() {
    	double robotVelocity = (Math.abs(rightEncoder.getRate()) + (Math.abs(leftEncoder.getRate())));
    	SmartDashboard.putNumber("Robot Velocity", robotVelocity);
    	return robotVelocity;
    }
    
    public double getEncoderDistance() {
    	double leftEncRaw = leftEncoder.get();
    	double rightEncRaw = rightEncoder.get();
    	SmartDashboard.putNumber("Left Enc Raw", leftEncRaw);
    	SmartDashboard.putNumber("Right Enc Raw", rightEncRaw);
    	double leftEncAdj = leftEncoder.getDistance();
    	double rightEncAdj = rightEncoder.getDistance();
    	SmartDashboard.putNumber("Left Enc Adj", leftEncAdj);
    	SmartDashboard.putNumber("Rigth Enc Adj", rightEncAdj);
    	double encoderDistance = (leftEncAdj + rightEncAdj) / 2;
    	return encoderDistance;
    }
    
    public double reportGyro() {
    	double robotAngle = navxGyro.getAngle();
    	double robotPitch = navxGyro.getPitch();
    	double robotRoll  = navxGyro.getRoll();
    	SmartDashboard.putNumber("navx Angle", robotAngle);
    	SmartDashboard.putNumber("navx Pitch", robotPitch);
    	SmartDashboard.putNumber("navx Roll", robotRoll);
    	return robotAngle;
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
}

