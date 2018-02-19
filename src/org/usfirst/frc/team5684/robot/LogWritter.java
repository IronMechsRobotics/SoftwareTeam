package org.usfirst.frc.team5684.robot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;

public class LogWritter {
	String path = " /media/sda1/" + RobotMap.LOGFILE;

	public LogWritter() {
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

	}

	public boolean writeLog(String log) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String temp = timestamp + "\t" + log;
			Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
		return true;
	}
}
