package org.usfirst.frc.team5684.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

import org.usfirst.frc.team5684.robot.RobotMap;
import org.usfirst.frc.team5684.robot.commands.DriveWithTwoJoysticks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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
	private Encoder left;
	private Encoder right;
	double maxPeriod=.1;
	int minRate=10;
	double distancePerPulse=.05;
	int samplesToAverage=7;
	public DriveTrain() {
		
		drive = new RobotDrive(RobotMap.leftWheels, RobotMap.rightWheels);
		deadZone = .1;
		positiveM = ((1 - 0) / (1 - deadZone));
		positiveB = (1 - positiveM);
		negativeM = ((-1 - 0) / (-1 + deadZone));
		negativeB = (1 - negativeM);
    	left= new Encoder(8, 9, true, Encoder.EncodingType.k4X);
    	left.setMaxPeriod(maxPeriod);
     	left.setMinRate(minRate);
     	left.setDistancePerPulse(RobotMap.distancePerWheelPulse);
     	left.setSamplesToAverage(samplesToAverage);
     	left.reset();
     	right=new Encoder(6,7,true,Encoder.EncodingType.k4X);
        right.setMaxPeriod(maxPeriod);
     	right.setMinRate(minRate);
     	right.setDistancePerPulse(RobotMap.distancePerWheelPulse);
     	right.setSamplesToAverage(samplesToAverage);
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

//		System.out.println("RIGHT+++++++++++++++++++++++++++++++++++++");
//		reportEncoder(right);
//		System.out.println("LEFT++++++++++++++++++++++++++++++++++++++");
//		reportEncoder(left);
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
		drive.arcadeDrive(forwardMovement, turnMovement);
	}
	
	public void moveMotors(double speed){
		drive.tankDrive(speed,speed);
	}
	
	public double getLeftDistance()
	{
		return left.getDistance();
	}
	public double getRightDistance()
	{
		return right.getDistance();
	}
	
	public void resetEncoder()
	{
		left.reset();
		right.reset();
	}

	private void reportEncoder(Encoder enc) {
		System.out.println("Raw is :" + enc.getRaw() + ":");
		System.out.println("Distance is :" + enc.getDistance() + ":");
		System.out.println("Direction is :" + enc.getDirection() + ":");
		System.out.println("Stopped is :" + enc.getStopped() + ":");
		System.out.println("Rate is :" + enc.getRate() + ":");

	}

	public LiveWindowSendable getLeftEncoder() {
		return left;
	}
}
