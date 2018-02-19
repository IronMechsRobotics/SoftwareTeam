package org.usfirst.frc.team5684.robot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Map;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class LogWritter {
	String path = " /media/sda1/" + RobotMap.LOGFILE;
	DriverStation ds;

	public LogWritter() {
		DriverStation ds = DriverStation.getInstance();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String data = timestamp + "\t" + "log file created";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("path");
			out.write(data.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writeLog("getAlliance" + ds.getAlliance());
		writeLog("getEventName()" + ds.getEventName());
		writeLog("getLocation()" + ds.getLocation());
		writeLog("getMatchNumber()" + ds.getMatchNumber());
		writeLog("getMatchType()" + ds.getMatchType());
		writeLog("getReplayNumber()" + ds.getReplayNumber());
		writeLog("isAutonomous()" + ds.isAutonomous());
		writeLog("isDisabled()" + ds.isDisabled());
		writeLog("isEnabled()" + ds.isEnabled());
		writeLog("getBatteryVoltage()" + RobotController.getBatteryVoltage());
	}

	public boolean writeLog(String log) {
		String status = "\t\tisDisabled/isAutonomous/isEnabled()" + ds.isDisabled() + "/" + ds.isEnabled() + "/"
				+ ds.isEnabled();
		String voltage ="\t\t" + RobotController.getBatteryVoltage();
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String temp = timestamp + "|" + ds.getMatchTime() + "\t" + log + "\n";
			Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
			Files.write(Paths.get(path), status.getBytes(), StandardOpenOption.APPEND);
			Files.write(Paths.get(path), voltage.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
		return true;
	}
}
