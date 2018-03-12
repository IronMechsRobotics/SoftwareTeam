package org.usfirst.frc.team5684.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RobotMap {

	// PWM Ports
	public static int RIGHTWHEELMOTOR = 0;
	public static int LEFTWHEELMOTOR = 1;
	public static int RIGHTCUBEMOTOR = 4;
	public static int LEFTCUBEMOTOR = 2;
	public static int RIGHTELEVATORMOTOR = 3;
	public static int LEFTELEVATORMOTOR = 5;
	public static int COLLECTORRASIEMOTOR = 6;
	
	//switch right cube with right elvator

	// DIO ports
	public static int GYROCALIBTAIONBUTTON = 0;
	public static int LEFTWHEELENCODERA = 1;
	public static int LEFTWHEELENCODERB = 2;
	public static int RIGHTWHEELENCODERA = 3;
	public static int RIGHTWHEELENCODERB = 4;
	public static int ELEVATORENCODERA = 5;
	public static int ELEVATORENCODERB = 6;
	public static int LOCATIONSWITCH = 7;
	public static int NEARFARSWITCH = 8;

	// Analog ports
	public static final int ULTRASONIC = 0;

	// CONSTANTS
	public static final double WHEELDIAMETER = 6;
	public static double pulsePerRevolutionLeft = 360;
	public static double pulsePerRevolutionRight = 250;
<<<<<<< HEAD
	public static double encoderGearRatio = 1;
	public static double gearRatio = 1;
	public static double fudgeFactor = 1;
	public static double distancePerWheelPulseLeft = Math.PI * wheelDiameter / pulsePerRevolutionLeft;
	public static double distancePerWheelPulseRight = Math.PI * wheelDiameter / pulsePerRevolutionRight;
	public static double SWITCHHEIGHT = 20.0;
	public static double LOWSCALEHEIGHT = 4 * 12 + 3;
	public static double MIDDLESCALEHEIGHT = 5 * 12 + 3;
	public static double HIGHSCALEHEIGHT = 6 * 12;
	public static final int FEET = 12;
	public static final int INCHES = 1;
<<<<<<< HEAD
	public static double SWITCHHEIGHT = 20.0 * INCHES;
	public static double LOWSCALEHEIGHT = 4 * FEET + 3 * INCHES;
	public static double MIDDLESCALEHEIGHT = 5 * FEET + 3 * INCHES;
	public static double HIGHSCALEHEIGHT = 6 * FEET;
=======
	public static final double distancePerWheelPulseLeft = Math.PI * WHEELDIAMETER / pulsePerRevolutionLeft;
	public static final double distancePerWheelPulseRight = Math.PI * WHEELDIAMETER / pulsePerRevolutionRight;
	public static final double SWITCHHEIGHT = 20.0;
	public static final double LOWSCALEHEIGHT = 4 * 12 + 3;
	public static final double MIDDLESCALEHEIGHT = 5 * 12 + 3;
	public static final double HIGHSCALEHEIGHT = 6 * 12;
	public static final int FEET = 12;
	public static final int INCHES = 1;
	public static final double WHEELROTATION = Math.PI * WHEELDIAMETER;
>>>>>>> 1b3449fd1c7d7b990530d66289d9261b7d1957d5
=======
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/
	public static final int TURNRIGHT = -90;
	public static final int TURNLEFT = 90;
	public static final double DISTANCETOSIDEDROP = (131 * INCHES + 187.5 * INCHES) /2 - 2 * FEET;
	public static final int DISTANCETOSCALE= 16 * FEET ;
	public static final int DISTANCETOPASSSWITCH = 196 * INCHES + 7 * INCHES ;
	public static final int DRIVEALONGSWITCH = 12 * FEET + 6 * INCHES;
	static final Date date = new Date();
	static final SimpleDateFormat formatter = new SimpleDateFormat("YYY-MM-dd_HH-mm-ss");
	static final String strDate = formatter.format(date);
	public static final String folderPath = "/u/log/" + RobotMap.strDate;
	public static final String LOGFILE = folderPath + "/log.txt";
	public static final String LOGVOLTAGE = folderPath + "/voltage.txt";
	public static boolean fileCreated = false;
	public static final double ELEVATORDOWNSPEED = -0.80;
	public static final double ELEVATORUPSPEED = 1;
	public static final DriverStation DS = DriverStation.getInstance();
	public static final double LOWERARMTIME = .75;
	public static int rightTrigger = 3;
	public static int leftTrigger = 2;

	public static boolean writeLog(String log) {
		if (!fileCreated) {
			File dir = new File(folderPath);
			System.out.println(folderPath);
			boolean successful = dir.mkdirs();
			if (successful) {
				System.out.println("We did it");
			} else {
				System.out.println("We didn't do it");
			}

			File tempLog;
			FileOutputStream fos = null;
			try {
				tempLog = new File(LOGFILE);
				fos = new FileOutputStream(tempLog);
				if (!tempLog.exists()) {
					tempLog.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileCreated = true;
		}

		String status = "\t\tisDisabled:\t " + DS.isDisabled() + "\r\n" + "\t\t isAutonomous\t " + DS.isAutonomous()
				+ "\r\n" + "\t\tisEnabled()\t " + DS.isEnabled() + "\r\n \r\n \r\n";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String voltage = System.currentTimeMillis() + "\t" + RobotController.getBatteryVoltage() + "\r\n";
		String temp = timestamp + "|" + DS.getMatchTime() + "\r\n\t" + log + "\r\n";
		try {
			BufferedWriter logFile = new BufferedWriter(new FileWriter(LOGFILE, true));
			logFile.append(temp);
			logFile.append(status);
			logFile.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("couldn't write log");
			return false;
		}
		try {
			BufferedWriter logFile = new BufferedWriter(new FileWriter(LOGVOLTAGE, true));
			logFile.append(voltage);
			logFile.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("couldn't write voltage log");
			return false;
		}
		return true;
	}
	
	public static double map(double x, double minInput, double maxInput, double minOutput, double maxOutput)
	{
		double slope =(maxOutput-minOutput)/(maxInput-minInput);
		double yInt=maxOutput-slope*x;
		return slope*x+yInt;
	}
}