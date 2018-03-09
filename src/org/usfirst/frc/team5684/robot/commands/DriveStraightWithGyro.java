package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithGyro extends Command {
	double distance;
	double kp = .45;

	public DriveStraightWithGyro(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		Robot.drivetrain.getLeftEncoder().reset();
		Robot.drivetrain.getRightEncoder().reset();
		this.distance = distance;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.writeLog("DriveStraightWithGyro initialize");
		Robot.drivetrain.resetEncoder();
		Robot.drivetrain.resetGyro();
		this.distance = distance;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("LeftWheels", Robot.drivetrain.getLeftEncoder().getDistance());
		SmartDashboard.putNumber("RightWheels", Robot.drivetrain.getRightEncoder().getDistance());
		SmartDashboard.putNumber("X: ", Robot.drivetrain.getGyro().getAngleX());
		SmartDashboard.putNumber("Y: ", Robot.drivetrain.getGyro().getAngleY());
		SmartDashboard.putNumber("Z: ", Robot.drivetrain.getGyro().getAngleZ());
		double angle = -1*Robot.drivetrain.getGyro().getAngleX();
		if (distance >= 0)
			Robot.drivetrain.simpleDrive(.66, angle * kp);
		else
			Robot.drivetrain.simpleDrive(-.66, -1 * angle * kp);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((Math.abs(Robot.drivetrain.getRightDistance() + Robot.drivetrain.getLeftDistance()) / 2.0) >= Math
				.abs(distance))
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
		RobotMap.writeLog("DriveStraightWithGyro end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
