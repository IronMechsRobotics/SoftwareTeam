package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.AutoCrossLine;
import org.usfirst.frc.team5684.robot.commands.AutoInsideDumpCube;
import org.usfirst.frc.team5684.robot.commands.AutoInsideHoldCubeLeft;
import org.usfirst.frc.team5684.robot.commands.AutoInsideHoldCubeRight;
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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	public static DriveTrain drivetrain;
	public static IO io;
	public static TurnSubsystem turn;

	public static boolean hasSeenAutonmous = false;
	public static ElevatorSubsystem elevator;
	public static CubeIntakeSystem cubeIntakeSystem;
	public DriverStation ds;
	public boolean isBlue;
	private Command selectedCommand;
	private DigitalInput nearFarSwitch;
	private DigitalInput locationSwitch;
	private DigitalInput gyroCalibrateButton;
	public long lastCalibration;
	public boolean hasCalibrated;
	public static boolean isRight;
	public static boolean isFar;
	public AnalogInput us;
	public long time;

	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		SmartDashboard.delete("Inside/Outside");
		SmartDashboard.delete("Left/Right");
		SmartDashboard.updateValues();
		System.out.println("Hello");
		hasCalibrated = false;
		lastCalibration = 0;
		gyroCalibrateButton = new DigitalInput(RobotMap.GYROCALIBTAIONBUTTON);
		locationSwitch = new DigitalInput(RobotMap.LOCATIONSWITCH);
		nearFarSwitch = new DigitalInput(RobotMap.NEARFARSWITCH);
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
		turn = new TurnSubsystem();
		SmartDashboard.putString("Gyro", "YOU FORGOT SOMETHING DRIVE TEAM");
		SmartDashboard.putString("Inside/Outside", "RAD");
		SmartDashboard.putString("Left/Right", "RAD");
		RobotMap.writeLog("Finished robotInit");
		SmartDashboard.updateValues();

		isFar = false;
	}

	public boolean wantToCalibrate() {
		return !gyroCalibrateButton.get();
	}

	public void robotPeriodic() {
		this.selectedCommand = this.chooser.getSelected();
		SmartDashboard.putString("Selected Autonomous", this.selectedCommand.getName());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		RobotMap.writeLog("Start AutonomusInit");
		/*if (!hasCalibrated) {
			SmartDashboard.putString("Gyro", "starting to calibrate");
			RobotMap.writeLog("Calibration started");
			drivetrain.calibrateGyro();
			elevator.resetEncoder();
			Robot.drivetrain.resetEncoder();
			lastCalibration = System.currentTimeMillis();
			SmartDashboard.putString("Gyro", "We have calibrated the Gyro... GOOD JOB");
			RobotMap.writeLog("Calibration ended");
		}*/
		ds = RobotMap.DS;
		String temp = "getAlliance\t" + ds.getAlliance();
		temp = temp + "\r\n" + "getAlliance\t" + ds.getAlliance();
		temp = temp + "\r\n" + "getEventName()\t" + ds.getEventName();
		temp = temp + "\r\n" + "getLocation()\t" + ds.getLocation();
		temp = temp + "\r\n" + "getMatchNumber()\t" + ds.getMatchNumber();
		temp = temp + "\r\n" + "getReplayNumber()\t" + ds.getReplayNumber() + "\r\n";
		RobotMap.writeLog(temp);
		RobotMap.writeLog("Getting Game data");
		String gameData = getGameData();
		RobotMap.writeLog("gameData \t " + gameData);
		RobotMap.writeLog("gameData.substring(0, 1): " + gameData.substring(0, 1) + "\t getIsLeft()" + getIsLeft());
		if (getFar()) {
			RobotMap.writeLog("We are going far");
			if (gameData.substring(0, 1).equalsIgnoreCase("L") && getIsLeft()) {
				RobotMap.writeLog("Auto Method: LL");
				new AutoSwitchLL().start();
			} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && getIsLeft()) {
				new AutoSwitchLR().start();
				RobotMap.writeLog("Auto Method: LR");
			} else if (gameData.substring(0, 1).equalsIgnoreCase("L") && !getIsLeft()) {
				new AutoSwitchRL().start();
				RobotMap.writeLog("Auto Method: RL");
			} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && !getIsLeft()) {
				new AutoSwitchRR().start();
				RobotMap.writeLog("Auto Method: RR");
			} else {
				RobotMap.writeLog("Auto Method: AutoCrossLine");
				new AutoCrossLine().start();
			}
		} else {
			if (gameData.substring(0, 1).equalsIgnoreCase("L") && getIsLeft()) {
				RobotMap.writeLog("Auto Method: close LL");
				new AutoInsideDumpCube().start();
			} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && getIsLeft()) {
				new AutoInsideHoldCubeLeft().start();
				RobotMap.writeLog("Auto Method: close LR");
			} else if (gameData.substring(0, 1).equalsIgnoreCase("L") && !getIsLeft()) {
				new AutoInsideHoldCubeRight().start();
				RobotMap.writeLog("Auto Method: close RL");
			} else if (gameData.substring(0, 1).equalsIgnoreCase("R") && !getIsLeft()) {
				RobotMap.writeLog("Auto Method: close RR");
				new AutoInsideDumpCube().start();
			} else {
				RobotMap.writeLog("Auto Method: AutoCrossLine CLOSE");
				new AutoCrossLine().start();
			}
		}

	}

	public static boolean getIsRight() {
		return isRight;
	}

	public static boolean getIsLeft() {
		return !isRight;
	}

	public static boolean getFar() {
		return isFar;
	}

	public static boolean getNear() {
		return !isFar;
	}

	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		if (wantToCalibrate()) {
			RobotMap.writeLog("wantToCalibrate was pressed");
			if (System.currentTimeMillis() >= (lastCalibration + 5000)) {
				SmartDashboard.putString("Gyro", "starting to calibrate");
				RobotMap.writeLog("Calibration started");
				drivetrain.calibrateGyro();
				elevator.resetEncoder();
				Robot.drivetrain.resetEncoder();
				lastCalibration = System.currentTimeMillis();
				SmartDashboard.putString("Gyro", "We have calibrated the Gyro... GOOD JOB");
				RobotMap.writeLog("Calibration ended");
			} else {
				RobotMap.writeLog("Calibration did not happen");
			}
		}
		isRight = locationSwitch.get();
		isFar = nearFarSwitch.get();
		if (isFar) {
			SmartDashboard.putString("A", "Outside");
		} else {
			SmartDashboard.putString("A", "Inside");
		}
		if (isRight) {
			SmartDashboard.putString("B", "RIGHT");

		} else {
			SmartDashboard.putString("B", "LEFT");
		}
		SmartDashboard.updateValues();
		this.selectedCommand = this.chooser.getSelected();
		SmartDashboard.putString("Selected Autonomous", this.selectedCommand.getName());
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
			RobotMap.writeLog("Getting Game data attempt");
		}
		return gameData;
	}

}
