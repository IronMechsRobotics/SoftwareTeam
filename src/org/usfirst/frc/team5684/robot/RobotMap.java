package org.usfirst.frc.team5684.robot;

public class RobotMap {
	//PWM Ports
	public static int rightWheelMotor = 0;
	public static int leftWheelMotor = 1;
	public static int rightSideCubeIntake = 2;
	public static int leftSideCubeIntake = 3;
	public static int ELEVATORMOTORRIGHT = 4;
	public static int ELEVATORMOTORLEFT = 5;
	
	//DIO ports
	public static int NEARFARSWITCHb  = 0;
	public static int LEFTWHEELA = 1;
	public static int LEFTWHEELB = 2;
	public static int RIGHTWHEELA = 3;
	public static int RIGHTWHEELB = 4;
	public static int ELEVATORENCODERA = 5;
	public static int ELEVATORENCODERB = 6;
	public static int LOCATIONSWITCH = 7;
	public static int GYROCALIBTAIONBUTTON = 8;
	
	//Analog ports
	public static final int ULTRASONIC = 0;
	
	//CONSTANTS
	public static double wheelDiameter = 6;
	public static double pulsePerRevolutionLeft = 360;
	public static double pulsePerRevolutionRight = 250;
	public static double encoderGearRatio = 1;
	public static double gearRatio = 1;
	public static double fudgeFactor = 1;
	public static double distancePerWheelPulseLeft = Math.PI * wheelDiameter / pulsePerRevolutionLeft;
	public static double distancePerWheelPulseRight = Math.PI * wheelDiameter / pulsePerRevolutionRight;


}