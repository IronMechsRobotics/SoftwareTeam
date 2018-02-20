package org.usfirst.frc.team5684.robot;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import edu.wpi.first.wpilibj.DriverStation;

public class LogWritter {
	DriverStation ds;
	File log;
	File log2;

	public LogWritter() {
		ds = DriverStation.getInstance();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String data = timestamp + "\t" + "log file created";
		new File("/u/logs/" + RobotMap.strDate).mkdirs();
		log = new File(RobotMap.LOGFILE);
		log2 = new File(RobotMap.LOGFILE2);
		if (!log.exists()) {
			try {
				log.createNewFile();
			} catch (IOException e) {
				System.out.println("Erro: " + e);
			}
		} else {

		}
		RobotMap.writeLog("Log Created");
	}
}
