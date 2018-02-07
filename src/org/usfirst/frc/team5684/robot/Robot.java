package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.GuaranteeSwitch;
import org.usfirst.frc.team5684.robot.commands.driveByDistance;
import org.usfirst.frc.team5684.robot.subsystems.CubeIntakeSystem;
import org.usfirst.frc.team5684.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5684.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	public static ADIS16448_IMU gyro;
	public long time;
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

	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		hasCalibrated = false;
		lastCalibration = 0;
		locationSwitch = new DigitalInput(RobotMap.LOCATIONSWITCH);
		gyroCalibrateButton = new DigitalInput(RobotMap.GYROCALIBTAIONBUTTON);
		drivetrain = new DriveTrain();
		elevator = new ElevatorSubsystem();
		gyro = new ADIS16448_IMU();
		gyro.calibrate();
		time = System.currentTimeMillis();
		cubeIntakeSystem = new CubeIntakeSystem();
		chooser.addDefault("GuaranteeSwitch", new GuaranteeSwitch());
		chooser.addObject("Second Auto Option", new driveByDistance(10));
		SmartDashboard.putData("Auto choices", this.chooser);
		ds = DriverStation.getInstance();
		io = new IO();
		SmartDashboard.putString("Gyro", "YOU FORGOT SOMETHING DRIVE TEAM");
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
		// The very first thing you do is get which color you are.
		if (ds.getAlliance() == DriverStation.Alliance.Blue) {
			isBlue = true;
		} else {
			isBlue = false;
		}
		this.autonomousCommand = this.selectedCommand;
		if (this.autonomousCommand != null) {
			this.autonomousCommand.start();
		}
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		hasSeenAutonmous = true;
	}

	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		if (( wantToCalibrate() && System.currentTimeMillis() >= (lastCalibration + 5000))) {
			gyro.calibrate();
			lastCalibration = System.currentTimeMillis();
			SmartDashboard.putString("Gyro", "We have calibrated the Gyro... GOOD JOB");
			System.out.println("We've been here");
		}
		Scheduler.getInstance().run();
		if (!hasSeenAutonmous && (System.currentTimeMillis() >= time + 30 * 1000)) {
			System.out.println("Recalibrate");
			time = System.currentTimeMillis();
			gyro.calibrate();
		}
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

	public static boolean getIsBlue() {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean getIsLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	public DriverStation getDS() {
		return ds;
	}
}
