package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class driveByDistancePID extends PIDSubsystem {
	private Victor motor;
	private Encoder enc;
	private String name;
    // Initialize your subsystem here
	DriveTrain driveTrain;
    public driveByDistancePID(Victor motor, Encoder enc, String name) {
    	super("DriveByDistance",.15,0,0);
    	this.driveTrain=Robot.drivetrain;
		this.motor = motor;
		this.enc = enc;
		this.setOutputRange(-1, 1);
    	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
        return this.enc.getDistance();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	motor.set(output);
    }
}
