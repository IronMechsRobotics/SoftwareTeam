package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;
import org.usfirst.frc.team5684.robot.subsystems.setSpeedPID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class testVelPID extends Command {

	public testVelPID() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.test(150);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stopTest();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
