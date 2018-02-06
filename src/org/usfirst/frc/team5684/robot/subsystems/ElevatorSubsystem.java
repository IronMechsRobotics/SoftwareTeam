package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

	Encoder cim;
	Spark motor;

	public ElevatorSubsystem() {
		cim = new Encoder(RobotMap.ELEVATORENCODERA, RobotMap.ELEVATORENCODERA);
		motor = new Spark(RobotMap.ELEVATORMOTOR);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setMotor(double speed) {
		motor.set(speed);
	}

	public void stopMotor() {
		motor.set(0);
	}

	public double getSpeed() {
		return cim.getRate();
	}
}
