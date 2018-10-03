package org.usfirst.frc.team5401.robot;

public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double DRIVE_SENSITIVITY_DEFAULT  =  1.0;
	public static final double DRIVE_SENSITIVITY_PRECISE  =  0.2;
	public static final double DRIVE_SPIN_SENSITIVITY     =  0.5;
	public static final double DRIVE_THRESHHOLD           =  0.1;
	
	//Controllers
	public static final int XBOX_CONTROLLER_DRIVER        =  0;
	public static final int XBOX_CONTROLLER_OPERATOR      =  1;
	
	//Buttons
	public static final int XBOX_A_DRIVER                 =  1;
	public static final int XBOX_B_DRIVER                 =  2;
	public static final int XBOX_X_DRIVER                 =  3;
	public static final int XBOX_Y_DRIVER                 =  4;
	public static final int XBOX_LEFT_BUMPER_DRIVER       =  5;
	public static final int XBOX_RIGHT_BUMPER_DRIVER      =  6;
	public static final int XBOX_BACK_DRIVER              =  7;
	public static final int XBOX_START_DRIVER             =  8;
	public static final int XBOX_L3_DRIVER                =  9;
	public static final int XBOX_R3_DRIVER                =  10;
	
	public static final int XBOX_A_OPERATOR               =  1;
	public static final int XBOX_B_OPERATOR               =  2;
	public static final int XBOX_X_OPERATOR               =  3;
	public static final int XBOX_Y_OPERATOR               =  4;
	public static final int XBOX_LEFT_BUMPER_OPERATOR     =  5;
	public static final int XBOX_RIGHT_BUMPER_OPERATOR    =  6;
	public static final int XBOX_BACK_OPERATOR            =  7;
	public static final int XBOX_START_OPERATOR           =  8;
	public static final int XBOX_L3_OPERATOR              =  9;
	public static final int XBOX_R3_OPERATOR              =  10;
	
	//Joysticks
	
	//Xbox Controller Axis
	public static final int LEFT_STICK_AXIS_X             =  0;
	public static final int LEFT_STICK_AXIS_Y             =  1;
	public static final int LEFT_TRIGGER_AXIS             =  2;
	public static final int RIGHT_TRIGGER_AXIS            =  3;
	public static final int RIGHT_STICK_AXIS_X            =  4;
	public static final int RIGHT_STICK_AXIS_Y            =  5;
	
	//PWM Motors
	public static final int LEFT_DRIVE_MOTOR_1            =  0;
	public static final int RIGHT_DRIVE_MOTOR_1           =  1;
	public static final int SHOOTER_MOTORS                =  2;
	public static final int INFEEDER_MOTOR                =  3;
	public static final int METERING_ROLLER               =  4;
	public static final int HOPPER_CONVEYOR               =  5;
	public static final int CLIMBER_MOTOR                 =  6;
	public static final int LEFT_DRIVE_MOTOR_2            =  7;
	public static final int RIGHT_DRIVE_MOTOR_2           =  8;
	
	//Sensor Channels
	public static final int LEFT_ENC_1                    =  2;
	public static final int LEFT_ENC_2                    =  3;
	public static final int RIGHT_ENC_1                   =  4;
	public static final int RIGHT_ENC_2                   =  5;
	
	//Pneumatics
	public static final int PCM_ID    = 0;
	public static final int SHIFTER_IN                    =  0;
	public static final int SHIFTER_OUT                   =  1;
	public static final int INFEEDER_OUT                  =  2;
	public static final int INFEEDER_IN                   =  3;
	public static final int UNJAMMER_IN                   =  4;
	public static final int UNJAMMER_OUT                  =  5;
	public static final int GEAR_MANIPULATOR_IN           =  6;
	public static final int GEAR_MANIPULATOR_OUT          =  7;
	
	//Analog Channels
	public static final int PRESSURE_SENSOR               =  0;

}