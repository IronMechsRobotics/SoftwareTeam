package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.subsystems.driveByDistancePID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class driveByDistance extends Command {
	driveByDistancePID leftPID;
	driveByDistancePID rightPID;
	double setPoint;

	public driveByDistance(double setPoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		leftPID = new driveByDistancePID(Robot.drivetrain.getLeftMotor(), Robot.drivetrain.getLeftEncoder(), "Left");
		rightPID = new driveByDistancePID(Robot.drivetrain.getRightMotor(), Robot.drivetrain.getRightEncoder(),
				"Right");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetEncoder();
		leftPID.setSetpoint(setPoint);
		rightPID.setSetpoint(setPoint);
		leftPID.enable();
		rightPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(leftPID.getSetpoint() - leftPID.getPosition()) < .5
				&& Math.abs(rightPID.getSetpoint() - rightPID.getPosition()) < .5;
	}

	// Called once after isFinished returns true
	protected void end() {
		leftPID.disable();
		rightPID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
