package org.usfirst.frc.team5684.robot;

public class RobotMap {
	public static int leftWheels = 0;
	public static int rightWheels = 1;
	public static int arm = 2;
	public static double wheelDiameter = 6;
	public static double pulsePerRevolutionLeft = 360;
	public static double pulsePerRevolutionRight = 250;
	public static double encoderGearRatio = 1;
	public static double gearRatio = 1;
	public static double fudgeFactor = 1;
	public static double distancePerWheelPulseLeft = Math.PI * wheelDiameter / pulsePerRevolutionLeft;
	public static double distancePerWheelPulseRight = Math.PI * wheelDiameter / pulsePerRevolutionRight;
	public static int rightSideCubeIntake = 2;
	public static int leftSideCubeIntake = 3;
	public static int LEFTWHEELA = 3;
	public static int LEFTWHEELB = 3;
	public static int RIGHTWHEELA = 3;
	public static int RIGHTWHEELB = 3;
	public static int ELEVATORENCODERA = 3;
	public static int ELEVATORENCODERB = 4;
	public static int ELEVATORMOTOR = 4;

}