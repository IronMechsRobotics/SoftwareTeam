package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	public Wait(double time) {
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.writeLog("Starting to wait");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.writeLog("end to wait");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
