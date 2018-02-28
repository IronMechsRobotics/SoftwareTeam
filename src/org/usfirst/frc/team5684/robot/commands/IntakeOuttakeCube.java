package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOuttakeCube extends Command {
	Joystick stick;
	double DEADBAND = .05;

	public IntakeOuttakeCube() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		stick = Robot.io.supportStick;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (stick.getRawAxis(1) <= -1 * DEADBAND) {
			Robot.cubeIntakeSystem.setMotors(1);
		} else if (stick.getRawAxis(1) >= DEADBAND) {
			Robot.cubeIntakeSystem.setMotors(RobotMap.map(stick.getRawAxis(1), DEADBAND, 1, -.45, -.95));
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeIntakeSystem.setMotors(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
