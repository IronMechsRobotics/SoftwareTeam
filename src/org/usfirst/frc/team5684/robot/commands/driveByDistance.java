package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.subsystems.driveByDistancePID;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class driveByDistance extends Command {
	driveByDistancePID pid;
	double setPoint;
    public driveByDistance(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setPoint=setPoint;
    	pid = new driveByDistancePID();
    	System.out.println(this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	pid.setSetpoint(setPoint);
    	
    	pid.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(pid.getSetpoint()-pid.getPosition()) <.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
