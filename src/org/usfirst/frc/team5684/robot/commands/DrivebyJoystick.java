package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivebyJoystick extends Command {
	private static double DEAD_BAND = 0.15;
	private static double ROTATE_DEAD_BAND= 0.15;

	public DrivebyJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double move = Robot.io.leftStick.getY();
		double rotate = Robot.io.rightStick.getX();
		boolean turbo = Robot.io.rightStick.getRawButton(1) || Robot.io.leftStick.getRawButton(1);
		if (Math.abs(move) < DEAD_BAND) {
			move = 0;
		} else {
			if (turbo) {
				move = map(move, 0, 1.0);
			} else {
				move = map(move, 0, 0.5);
			}
		}
		if (Math.abs(rotate) < ROTATE_DEAD_BAND) {
			rotate = 0;
		} else {
			if (turbo) {
				rotate = map(rotate, 0, 0.6);
			} else {
				rotate = map(rotate, 0, 0.3);
			}
		}

		Robot.drivetrain.drive(move, rotate);
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
