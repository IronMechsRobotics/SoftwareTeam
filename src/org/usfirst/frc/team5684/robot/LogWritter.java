package org.usfirst.frc.team5684.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Map;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class LogWritter {
	String path = "/u/logs/" + RobotMap.LOGFILE;
	DriverStation ds;
	File log;

	public LogWritter() {
		ds = DriverStation.getInstance();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String data = timestamp + "\t" + "log file created";
		new File("/u/logs").mkdirs();
		log = new File(path);
		if (!log.exists()) {
			System.out.println("Make a new file");
			try {
				log.createNewFile();
				System.out.println("File created");
			} catch (IOException e) {
				System.out.println("no new file");
				System.out.println(e);
				e.printStackTrace();
			}
		} else {
			System.out.println("No need to make a new file");
		}
		ds = DriverStation.getInstance();
		String temp = "getAlliance\t" + ds.getAlliance();
		temp = temp + "\r\n" + "getAlliance\t" + ds.getAlliance();
		temp = temp + "\r\n" + "getEventName()\t" + ds.getEventName();
		temp = temp + "\r\n" + "getLocation()\t" + ds.getLocation();
		temp = temp + "\r\n" + "getMatchNumber()\t" + ds.getMatchNumber();
		temp = temp + "\r\n" + "getReplayNumber()\t" + ds.getReplayNumber() + "\r\n";
		RobotMap.writeLog(temp);
	}
}
