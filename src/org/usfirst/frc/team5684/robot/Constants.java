package org.usfirst.frc.team5684.robot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class Constants {
	// Robot
	public static double wheelDiameter = 6;
	public static double pulsePerRevolutionLeft = 360;
	public static double pulsePerRevolutionRight = 250;
	public static double distancePerWheelPulseLeft = Math.PI * wheelDiameter / pulsePerRevolutionLeft;
	public static double distancePerWheelPulseRight = Math.PI * wheelDiameter / pulsePerRevolutionRight;
	public static final double ELEVATORDOWNSPEED = -.5;
	public static final double ELEVATORUPSPEED = .9;

	// math
	public static final int FEET = 12;
	public static final int INCHES = 1;
	public static final int TURNRIGHT = -90;
	public static final int TURNLEFT = 90;

	// field
	public static double SWITCHHEIGHT = 20.0;
	public static double LOWSCALEHEIGHT = 4 * 12 + 3;
	public static double MIDDLESCALEHEIGHT = 5 * 12 + 3;
	public static double HIGHSCALEHEIGHT = 6 * 12;
	public static final int DISTANCETOSIDEDROP = 160 * INCHES;
	public static final int DISTANCETOPASSSWITCH = 196 * INCHES + 1 * FEET;
	public static final int DRIVEALONGSWITCH = 13 * FEET;

	public static final String LOGFILE = System.currentTimeMillis() + ".txt";

	public static final DriverStation DS = DriverStation.getInstance();
	static String path = "/u/logs/" + Constants.LOGFILE;
	public static int rightTrigger = 3;
	public static int leftTrigger = 2;

	public static boolean writeLog(String log) {
		String status = "\t\tisDisabled/isAutonomous/isEnabled()" + DS.isDisabled() + "/" + DS.isEnabled() + "/"
				+ DS.isEnabled() + "\r\n";
		String voltage = "\t\tVoltaage: " + RobotController.getBatteryVoltage() + "\r\n";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String temp = timestamp + "|" + DS.getMatchTime() + "\r\n\t" + log + "\r\n";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			writer.append(temp);
			writer.append(status);
			writer.append(voltage);
			writer.close();
		} catch (IOException e) {
			System.out.println("couldn't write");
		}
		return true;
	}
}
