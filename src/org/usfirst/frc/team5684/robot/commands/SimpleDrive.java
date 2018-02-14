package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SimpleDrive extends Command {
	private static double DEAD_BAND = 0.15;
	private static double ROTATE_DEAD_BAND = 0.15;

	public SimpleDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Joystick left = Robot.io.leftStick;
		Joystick right = Robot.io.rightStick;
		Robot.drivetrain.simpleDrive(-1*map(left.getRawAxis(1),-1,1,-1,1),map(right.getRawAxis(0),-1,1,-1,.1));
		SmartDashboard.putNumber("LeftWheels",Robot.drivetrain.getLeftEncoder().getRate());
		SmartDashboard.putNumber("RightWheels",Robot.drivetrain.getRightEncoder().getRate());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private static double map(double x, double out_min, double out_max) {
		return map(x, DEAD_BAND, 1.0, out_min, out_max);
	}

	private static double map(double x, double in_min, double in_max, double out_min, double out_max) {
		boolean negative = x < 0;
		double newValue = (Math.abs(x) - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
		if (negative) {
			return -newValue;
		} else {
			return newValue;
		}
	}
}
