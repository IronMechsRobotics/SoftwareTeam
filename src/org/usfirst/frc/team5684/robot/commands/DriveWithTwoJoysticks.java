package org.usfirst.frc.team5684.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5684.robot.Robot;

/**
 *
 */
public class DriveWithTwoJoysticks extends Command {
	

    public DriveWithTwoJoysticks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//this is where we set everything up
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(new Joystick(0),new Joystick(1));
    	//this is when the command is actually doing its thing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//check to see if you are done
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//what to do when you are done
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//what to do if things go wrong
    	this.end();
    }
}
