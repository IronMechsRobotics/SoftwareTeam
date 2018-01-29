package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class driveByDistancePID extends PIDSubsystem {

    // Initialize your subsystem here
	DriveTrain driveTrain;
    public driveByDistancePID() {
    	super("DriveByDistance",.1,0,0);
    	this.driveTrain=Robot.drivetrain;
    	driveTrain.resetEncoder();
    	this.setOutputRange(-.5, .5);
    	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
        return driveTrain.getLeftDistance();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	driveTrain.moveMotors(output);
    }
}
