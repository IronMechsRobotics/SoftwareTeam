package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightWithGyro extends Command {
	double distance;
	double kp = .3;

	public DriveStraightWithGyro(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		Robot.drivetrain.getLeftEncoder().reset();
		Robot.drivetrain.getRightEncoder().reset();
		this.distance=distance;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.getLeftEncoder().reset();
		Robot.drivetrain.getRightEncoder().reset();
		Robot.drivetrain.resetGyro();
		this.distance = distance;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angle = Robot.drivetrain.getGyro().getAngleZ();
		Robot.drivetrain.simpleDrive(.66, angle * kp);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (((Robot.drivetrain.getRightDistance() + Robot.drivetrain.getLeftDistance()) / 2.0) >= distance)
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
