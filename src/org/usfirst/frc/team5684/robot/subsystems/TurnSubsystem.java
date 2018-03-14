package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class TurnSubsystem extends PIDSubsystem {

	private final double DELTA = .05;
	private double currentSpeed;

	// Initialize your subsystem here
	public TurnSubsystem() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.

		super("Turn", .25, .05, .75);
		currentSpeed = 0;

	}

	public void initDefaultCommand() {
		this.setOutputRange(-1, 1);
		this.setAbsoluteTolerance(1);

	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		double temp = -1 * Robot.drivetrain.getGyro().getAngleX();
		currentSpeed = Math.max(Math.min(temp, currentSpeed + DELTA), currentSpeed - DELTA);
		return currentSpeed;
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		Robot.drivetrain.turn(-1 * output);
	}
}
