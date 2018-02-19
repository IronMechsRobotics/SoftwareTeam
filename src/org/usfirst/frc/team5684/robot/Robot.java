package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.AutoCrossLine;
import org.usfirst.frc.team5684.robot.commands.AutoSwitchLL;
import org.usfirst.frc.team5684.robot.commands.AutoSwitchLR;
import org.usfirst.frc.team5684.robot.commands.AutoSwitchRL;
import org.usfirst.frc.team5684.robot.commands.AutoSwitchRR;
import org.usfirst.frc.team5684.robot.commands.DrivebyJoystick;
import org.usfirst.frc.team5684.robot.subsystems.CubeIntakeSystem;
import org.usfirst.frc.team5684.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5684.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team5684.robot.subsystems.TurnSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	// make a statement like this for each new subsystem
	public static DriveTrain drivetrain;
	public static IO io;
	public static TurnSubsystem turn;

	public static boolean hasSeenAutonmous = false;
	public static ElevatorSubsystem elevator;
	public static CubeIntakeSystem cubeIntakeSystem;
	public DriverStation ds;
	public boolean isBlue;
	private Command selectedCommand;
	private Command autonomousCommand;
	private DigitalInput locationSwitch;
	private DigitalInput gyroCalibrateButton;
	public long lastCalibration;
	public boolean hasCalibrated;
	public static boolean isRight;
	public AnalogInput us;
	public long time;
	public boolean switchMatch;
	public boolean scaleMatch;
	public static LogWritter lw;

	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		LogWritter lw = new LogWritter();
		hasCalibrated = false;
		lastCalibration = 0;
		// locationSwitch = new DigitalInput(RobotMap.LOCATIONSWITCH);
		gyroCalibrateButton = new DigitalInput(RobotMap.GYROCALIBTAIONBUTTON);
		locationSwitch = new DigitalInput(RobotMap.LOCATIONSWITCH);
		drivetrain = new DriveTrain();
		elevator = new ElevatorSubsystem();

		time = System.currentTimeMillis();
		cubeIntakeSystem = new CubeIntakeSystem();
		// chooser.addObject("Simple Auto", new SimpleAuto());
		chooser.addDefault("GoForSwitch", new DrivebyJoystick());
		// chooser.addObject("GuaranteeSwitch", new GoForSwitch());
		// chooser.addObject("GuaranteeSwitch2", new GoForSwitch());
		SmartDashboard.putData("Auto choices", this.chooser);
		ds = DriverStation.getInstance();
		io = new IO();
		us = new AnalogInput(RobotMap.ULTRASONIC);
		turn = new TurnSubsystem();
		SmartDashboard.putString("Gyro", "YOU FORGOT SOMETHING DRIVE TEAM");
		lw.writeLog("Finished robotInit");
	}

	public boolean wantToCalibrate() {
		return !gyroCalibrateButton.get();
	}

	public void robotPeriodic() {

		this.selectedCommand = this.chooser.getSelected();
		SmartDashboard.putString("Selected Autonomous", this.selectedCommand.getName());
		SmartDashboard.putNumber("us", us.getValue() * .1367 - 2.7992);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		lw.writeLog("Start AutonomusInit");
		lw.writeLog("Getting Game data");
		String gameData = getGameData();
		lw.writeLog("gameData \t " + gameData);
		lw.writeLog(gameData.substring(0, 1) + "\t" + getIsLeft());
		if (gameData.substring(0, 1).equalsIgnoreCase("L") && getIsLeft()) {
			lw.writeLog("LL");
			new AutoSwitchLL().start();
		} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && getIsLeft()) {
			new AutoSwitchLR().start();
			lw.writeLog("LR");
		} else if (gameData.substring(0, 1).equalsIgnoreCase("L") && !getIsLeft()) {
			new AutoSwitchRL().start();
			lw.writeLog("RL");
		} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && !getIsLeft()) {
			new AutoSwitchRR().start();
			lw.writeLog("RR");
		} else {
			lw.writeLog("AutoCrossLine");
			new AutoCrossLine().start();
		}
	}

	public static boolean getIsRight() {
		return isRight;
	}

	public static boolean getIsLeft() {
		return !isRight;
	}

	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		if (wantToCalibrate()) {
			lw.writeLog("wantToCalibrate was pressed");
			if (System.currentTimeMillis() >= (lastCalibration + 5000)) {
				lw.writeLog("Calibration started");
				drivetrain.calibrateGyro();
				elevator.resetEncoder();
				Robot.drivetrain.resetEncoder();
				lastCalibration = System.currentTimeMillis();
				SmartDashboard.putString("Gyro", "We have calibrated the Gyro... GOOD JOB");
				lw.writeLog("Calibration ended");
			} else {
				lw.writeLog("Calibration did not happen");
			}
		}
		isRight = locationSwitch.get();
		if (isRight) {
			SmartDashboard.putString("Side", "RIGHT");

		} else {
			SmartDashboard.putString("Side", "LEFT");
		}

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	public DriverStation getDS() {
		return ds;
	}

	public String getGameData() {
		String gameData = "";
		long stopTime = System.currentTimeMillis() + 3 * 1000;
		// wait 3 seconds if no data return ""
		while (gameData.length() < 3 || System.currentTimeMillis() >= stopTime) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		return gameData;
	}

}
