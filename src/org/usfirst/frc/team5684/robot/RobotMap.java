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
	public static int RIGHTCUBEMOTOR = 2;
	public static int LEFTCUBEMOTOR = 3;
	public static int RIGHTELEVATORMOTOR = 4;
	public static int LEFTELEVATORMOTOR = 5;
	public static int COLLECTORRASIEMOTOR = 6;

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
	public static int LIMITSWITCHELEVATOR = 9;

	// Analog ports
	public static final int ULTRASONIC = 0;

	// CONSTANTS
	public static final double WHEELDIAMETER = 6;
	public static double pulsePerRevolutionLeft = 360;
	public static double pulsePerRevolutionRight = 250;
	public static final double distancePerWheelPulseLeft = Math.PI * WHEELDIAMETER / pulsePerRevolutionLeft;
	public static final double distancePerWheelPulseRight = Math.PI * WHEELDIAMETER / pulsePerRevolutionRight;
	public static final double SWITCHHEIGHT = 20.0;
	public static final double LOWSCALEHEIGHT = 4 * 12 + 3;
	public static final double MIDDLESCALEHEIGHT = 5 * 12 + 3;
	public static final double HIGHSCALEHEIGHT = 6 * 12;
	public static final int FEET = 12;
	public static final int INCHES = 1;
	public static final double WHEELROTATION = Math.PI * WHEELDIAMETER;
	public static final int TURNRIGHT = -90;
	public static final int TURNLEFT = 90;
	public static final int DISTANCETOSIDEDROP = 160 * INCHES;
	public static final int DISTANCETOPASSSWITCH = 196 * INCHES + 1 * FEET;
	public static final int DRIVEALONGSWITCH = 13 * FEET;
	static final Date date = new Date();
	static final SimpleDateFormat formatter = new SimpleDateFormat("YYY-MM-dd_HH-mm-ss");
	static final String strDate = formatter.format(date);
	public static final String folderPath = "/u/log/" + RobotMap.strDate;
	public static final String LOGFILE = folderPath + "/log.txt";
	public static final String LOGVOLTAGE = folderPath + "/voltage.txt";
	public static boolean fileCreated = false;
	public static final double ELEVATORDOWNSPEED = -.65;
	public static final double ELEVATORUPSPEED = .85;
	public static final DriverStation DS = DriverStation.getInstance();
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
		String status ="";
		if(DS.isDisabled())
		{
			status = "\t\tDisabled:\t ";
		}
		else if (DS.isAutonomous())
		{
			status = "\t\tisAutonomous:\t ";
		}
		else if (DS.isEnabled())
		{
			status = "\t\tisEnabled:\t ";
		}
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
}