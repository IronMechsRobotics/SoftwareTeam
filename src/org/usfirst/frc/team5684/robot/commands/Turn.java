package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Turn extends Command {

	private double setpoint;

	public Turn(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.setpoint = setpoint;
		requires(Robot.drivetrain);
		// setTimeout(3);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.writeLog("Turn initialize");
		Robot.drivetrain.simpleDrive(0, 0);
		Robot.drivetrain.resetGyro();
		double set = setpoint + Robot.drivetrain.getGyro().getAngleX();
		Robot.turn.setSetpoint(set);
		Robot.turn.enable();
		RobotMap.writeLog("setPoint: " + set);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.turn.onTarget())
			return true;
		else
			return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.simpleDrive(0, 0);
		Robot.turn.disable();
		RobotMap.writeLog("Turn end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

		end();
	}
}