package org.usfirst.frc.team5684.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;
import org.usfirst.frc.team5684.robot.commands.DrivebyJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private Encoder leftEncoder;
	private Encoder rightEncoder;
	double maxPeriod = .1;
	int minRate = 10;
	int samplesToAverage = 7;
	public static final double WHEEL_DIAMETER = 6;
	public static final double velF = 1.4;
	private static final double velP = 0.8999999999999999;
	private static final double velI = 0;
	private static final double velD = 0.03125000000025;
	private static final double posP = 0.4;
	private static final double posI = 0.0;
	private static final double posD = 0.0;
	private static final double MAX_SPEED = 130;
	private Victor left;
	private Victor right;
	private boolean correcting = false;
	private boolean driveBySpeed = true;
	private setSpeedPID leftPID;
	private setSpeedPID rightPID;

	public DriveTrain() {
		left = new Victor(RobotMap.leftWheels);
		right = new Victor(RobotMap.rightWheels);
		leftEncoder = new Encoder(RobotMap.LEFTWHEELA, RobotMap.LEFTWHEELB, true, Encoder.EncodingType.k4X);
		leftEncoder.setMaxPeriod(maxPeriod);
		leftEncoder.setMinRate(minRate);
		leftEncoder.setDistancePerPulse(RobotMap.distancePerWheelPulseLeft);
		leftEncoder.setSamplesToAverage(samplesToAverage);
		leftEncoder.setReverseDirection(true);
		rightEncoder = new Encoder(RobotMap.RIGHTWHEELA, RobotMap.RIGHTWHEELB, true, Encoder.EncodingType.k4X);
		rightEncoder.setMaxPeriod(maxPeriod);
		rightEncoder.setMinRate(minRate);
		rightEncoder.setDistancePerPulse(RobotMap.distancePerWheelPulseRight);
		rightEncoder.setSamplesToAverage(samplesToAverage);
		rightEncoder.setReverseDirection(false);
		leftEncoder.reset();
		rightEncoder.reset();
		leftPID = new setSpeedPID(left, leftEncoder, "Left", velP, velI, velD, velF);
		rightPID = new setSpeedPID(right, rightEncoder, "Right", velP, velI, velD, velF);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DrivebyJoystick());

	}

	/**
	 * This a a method that should be able to drive using the encoders. A lot of
	 * debugging to do here.
	 * 
	 * @param moveValue
	 *            - forward speed
	 * @param rotateValue
	 *            - turning speed
	 */
	public void drive(double moveValue, double rotateValue) {
		if (rotateValue == 0 && moveValue != 0) {
			if (!this.correcting) {
				// TODO must create turnPID
				this.resetGyro();
				// this.turnPId.reset();
				// this.turnPid.enable();
				// this.turnPid.setSetpoint(0);
				this.correcting = true;
			}
			// rotateValue= this.turnPID.get() + turnF;
		} else if (this.correcting) {
			this.correcting = false;
		}

		double leftMotorSpeed;
		double rightMotorSpeed;

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, -rotateValue);
			} else {
				rightMotorSpeed = moveValue + rotateValue;
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}

		if (this.driveBySpeed) {
			this.driveSpeed(-leftMotorSpeed * MAX_SPEED, rightMotorSpeed * MAX_SPEED);
		}

		SmartDashboard.putNumber("Move Output", moveValue);
		SmartDashboard.putNumber("Turn Output", rotateValue);

		// SmartDashboard.putNumber("Turn setpoint",this.turnPID.getSetpoint());
		SmartDashboard.putNumber("LeftPid Position", leftPID.getPosition());
		SmartDashboard.putNumber("LeftPid setPoint", leftPID.getSetpoint());

	}



	public void driveSpeed(double left, double right) {
		leftPID.setSetpoint(left);
		rightPID.setSetpoint(right);
		leftPID.enable();
		rightPID.enable();
	}

	public void resetGyro() {
		if (Robot.gyro != null) {
			Robot.gyro.reset();
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

	public double getLeftSpeed() {
		return leftEncoder.getRate();
	}

	public double getRightSpeed() {
		return rightEncoder.getRate();
	}

	public void resetEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public Victor getLeftMotor() {
		return left;
	}

	public Victor getRightMotor() {
		return right;
	}

	public void reportEncoder(Encoder enc) {
		 System.out.println("Raw is :" + enc.getRaw() + ":");
		 System.out.println("Distance is :" + enc.getDistance() + ":");
		 System.out.println("Direction is :" + enc.getDirection() + ":");
		 System.out.println("Stopped is :" + enc.getStopped() + ":");
		 System.out.println("Rate is :" + enc.getRate() + ":");

		System.out.println(System.currentTimeMillis() + "\t\t" + enc.getRate());

	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
}
