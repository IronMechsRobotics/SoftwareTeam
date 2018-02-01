package org.usfirst.frc.team5684.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class setSpeedPID extends PIDSubsystem {
	private Victor motor;
	private Encoder enc;
	private String name;

	// Initialize your subsystem here
	public setSpeedPID(Victor motor, Encoder enc, String name) {
		super(name, 1.0, 0, 0);
		this.motor = motor;
		this.enc = enc;
		this.setOutputRange(-.4, .4);
		this.setAbsoluteTolerance(1);
		this.setContinuous(true);

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return enc.getRate();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		motor.set(output);
	}
}
