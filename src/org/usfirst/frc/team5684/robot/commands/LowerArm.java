package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArm extends Command {

	public LowerArm() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.writeLog("LowerArm Init");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.cubeIntakeSystem.setArmMotor(.4);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeIntakeSystem.setArmMotor(0);
		RobotMap.writeLog("LowerArm end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
