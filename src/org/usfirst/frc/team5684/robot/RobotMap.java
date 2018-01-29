package org.usfirst.frc.team5684.robot;

public class RobotMap {
	public static int leftWheels=0;
	public static int rightWheels=1;
	public static int arm=2;
	public static double wheelDiameter=6;
	public static double pulsePerRevolution=360;
	public static double encoderGearRatio=1;
	public static double gearRatio=1;
	public static double fudgeFactor=1;
	public static double distancePerWheelPulse=Math.PI*wheelDiameter/pulsePerRevolution;
}