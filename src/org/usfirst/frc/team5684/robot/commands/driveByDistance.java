package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.subsystems.driveByDistancePID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		rightPID = new driveByDistancePID(Robot.drivetrain.getRightMotor(), Robot.drivetrain.getRightEncoder(),
				"Right");
		leftPID = new driveByDistancePID(Robot.drivetrain.getLeftMotor(), Robot.drivetrain.getLeftEncoder(),
				"Left");
		this.setPoint = setPoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetEncoder();
		rightPID.setSetpoint(setPoint);
		leftPID.setSetpoint(-1*setPoint);
		System.out.println("New setpoint: " + setPoint);
		rightPID.enable();
		leftPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.reportEncoder(Robot.drivetrain.getLeftEncoder());
		SmartDashboard.putNumber("rightPID Rate", Robot.drivetrain.getRightSpeed());
		SmartDashboard.putNumber("rightPID distance", Robot.drivetrain.getRightDistance());
		SmartDashboard.putNumber("leftPID Rate", Robot.drivetrain.getLeftSpeed());
		SmartDashboard.putNumber("leftPID distance", Robot.drivetrain.getLeftDistance());
		// System.out.println("left \t: " + leftPID.getSetpoint() + "\t" +
		// leftPID.getPosition());
		// System.out.println("right \t: " + rightPID.getSetpoint() + "\t" +
		// rightPID.getPosition());
		// System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n\n");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		/*if (Math.abs(this.setPoint - Robot.drivetrain.getRightDistance()) < .5) {
			Robot.drivetrain.getRightMotor().set(0);
			rightPID.disable();
		}

		return Math.abs(this.setPoint - Robot.drivetrain.getRightDistance()) < .5;*/
		return Robot.drivetrain.getLeftEncoder().getStopped() 
				&& Robot.drivetrain.getRightEncoder().getStopped();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setVoltage(0);
		rightPID.disable();
		leftPID.disable();
		System.out.println("DONE");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
