package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorToHeight extends Command {

	double goalHeight;
	boolean isGoingUp;

	public ElevatorToHeight(double inputHeight) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
		goalHeight = inputHeight;
		if (Robot.elevator.getDistance() > goalHeight) {
			isGoingUp = false;
		} else {
			isGoingUp = true;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(isGoingUp)
		{
			Robot.elevator.setMotor(.5);
			SmartDashboard.putNumber("Elevator distance", Robot.elevator.getDistance());
		}
		else
		{
			Robot.elevator.setMotor(-.5);
			SmartDashboard.putNumber("Elevator distance", Robot.elevator.getDistance());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isGoingUp && Robot.elevator.getDistance() > goalHeight) {
			return true;
		} else if (!isGoingUp && Robot.elevator.getDistance() < goalHeight) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.stopMotor();
		SmartDashboard.putNumber("Elevator distance", Robot.elevator.getDistance());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}