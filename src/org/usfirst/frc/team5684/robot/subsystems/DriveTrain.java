package org.usfirst.frc.team5684.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

import org.usfirst.frc.team5684.robot.RobotMap;
import org.usfirst.frc.team5684.robot.commands.DriveWithTwoJoysticks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static RobotDrive drive;
	private double deadZone;
	private double positiveM;
	private double positiveB;
	private double negativeM;
	private double negativeB;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	double maxPeriod = .1;
	int minRate = 10;
	double distancePerPulse = .05;
	int samplesToAverage = 7;
	public static final double WHEEL_DIAMETER = 6;
	public static final double velF = 1.4;
	private static final double velP = 0.8999999999999999;
	private static final double cvelI = 0;
	private static final double velD = 0.03125000000025;
	private static final double posP = 0.4;
	private static final double posI = 0.0;
	private static final double posD = 0.0;
	private Victor left = new Victor(RobotMap.leftWheels);
	private Victor right = new Victor(RobotMap.rightWheels);
	public DriveTrain() {

		deadZone = .1;
		positiveM = ((1 - 0) / (1 - deadZone));
		positiveB = (1 - positiveM);
		negativeM = ((-1 - 0) / (-1 + deadZone));
		negativeB = (1 - negativeM);
		leftEncoder = new Encoder(8, 9, true, Encoder.EncodingType.k4X);
		leftEncoder.setMaxPeriod(maxPeriod);
		leftEncoder.setMinRate(minRate);
		leftEncoder.setDistancePerPulse(RobotMap.distancePerWheelPulse);
		leftEncoder.setSamplesToAverage(samplesToAverage);
		leftEncoder.reset();
		rightEncoder = new Encoder(6, 7, true, Encoder.EncodingType.k4X);
		rightEncoder.setMaxPeriod(maxPeriod);
		rightEncoder.setMinRate(minRate);
		rightEncoder.setDistancePerPulse(RobotMap.distancePerWheelPulse);
		rightEncoder.setSamplesToAverage(samplesToAverage);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithTwoJoysticks());
		drive.setSafetyEnabled(false);

	}

	public void drive(Joystick leftStick, Joystick rightStick) {

		// System.out.println("RIGHT+++++++++++++++++++++++++++++++++++++");
		// reportEncoder(right);
		// System.out.println("LEFT++++++++++++++++++++++++++++++++++++++");
		reportEncoder(leftEncoder);
		double forwardMovement = leftStick.getY();
		double turnMovement = rightStick.getX();
		if (Math.abs(forwardMovement) > deadZone) {
			if (forwardMovement < 0) {
				forwardMovement = forwardMovement * negativeM + negativeB;
			} else {
				forwardMovement = forwardMovement * positiveM + positiveB;
			}
		} else {
			forwardMovement = 0;
		}
		if (Math.abs(turnMovement) > deadZone) {

		} else {
			turnMovement = 0;
		}
	}

	public void setVoltage(double voltage) {
		left.set(voltage);
		right.set(voltage);
	}

	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightDistance() {
		return rightEncoder.getDistance();
	}

	public void resetEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	private void reportEncoder(Encoder enc) {
		// System.out.println("Raw is :" + enc.getRaw() + ":");
		// System.out.println("Distance is :" + enc.getDistance() + ":");
		// System.out.println("Direction is :" + enc.getDirection() + ":");
		// System.out.println("Stopped is :" + enc.getStopped() + ":");
		// System.out.println("Rate is :" + enc.getRate() + ":");

		System.out.println(System.currentTimeMillis() + "\t\t" + enc.getRate());

	}

	public LiveWindowSendable getLeftEncoder() {
		return leftEncoder;
	}
}
