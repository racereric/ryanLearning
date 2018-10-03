package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
/**
 *
 */
public class XboxMove extends Command {
	private final double MAXIMUM_VELOCITY_FOR_LOW_GEAR;
	private final double MINIMUM_VELOCITY_FOR_HIGH_GEAR;
	
	double velocitySample1;
	double velocitySample2;

    public XboxMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	velocitySample1 = 0;
    	velocitySample2 = 0;
    	
    	MINIMUM_VELOCITY_FOR_HIGH_GEAR = 35;
    	MAXIMUM_VELOCITY_FOR_LOW_GEAR = 45;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.drivebase.reportGyro();
    	SmartDashboard.putNumber("Gyro", angle);
    	Robot.drivebase.reportGyro();
    	
    	double slew = Robot.oi.readXboxLeftX_Driver() * -1;
    	
    	double forward = Robot.oi.readXboxRightTrigger_Driver();
    	double reverse = Robot.oi.readXboxLeftTrigger_Driver();
    	boolean precision = Robot.oi.getPrecision_Driver();
    	boolean turn = Robot.oi.getTurnButton_Driver();
    	
    	boolean gearShiftLow = Robot.oi.getXboxBack_Driver();
    	boolean gearShiftHigh = Robot.oi.getXboxStart_Driver();
    	
    	if (gearShiftHigh) {
    	Robot.drivebase.shiftGearLowToHigh();	
    } else if (gearShiftLow) {
    	Robot.drivebase.shiftGearHighToLow();
    }
    	
    	double right = 0, left = 0, sensitivity;
    	
    	if (precision) {
    	sensitivity = RobotMap.DRIVE_SENSITIVITY_PRECISE;	
    } else {
    	sensitivity = RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    }
    	if (!turn) {
    		if (slew > RobotMap.DRIVE_THRESHHOLD) {  //If Slew is positive (joystick pushed to the right), go Right
    		left = (forward - reverse) * sensitivity;  //Send Left full power
    		right = (forward - reverse) * sensitivity * (1 - slew);  //Send right partial power, based on how far the joystick is being pushed
    	} else if (slew < (-1 * RobotMap.DRIVE_THRESHHOLD)) {  //If Slew is negative (joystick pushed to the left), go Left
    		left = (forward - reverse) * sensitivity * (1 + slew);  //Send Left partial power, based on how far the joystick is being pushed
    		right = (forward - reverse) * sensitivity;  //Send Right full power
    	} else {  //Drive forward/backward normally
    		left = (forward - reverse) * sensitivity;
    		right = (forward - reverse) * sensitivity;
    	}
    } else {
    	if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD) {
    		left = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    		right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
      }
    }
    	Robot.drivebase.drive(left, right);
    	
    	Robot.drivebase.getEncoderDistance();
    	
    	velocitySample2 = Robot.drivebase.reportRobotVelocity();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.stopDriveBase();
    	System.out.println("XboxMove end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stopDriveBase();
    	System.out.println("XboxMove Interrupted");
    }
}
