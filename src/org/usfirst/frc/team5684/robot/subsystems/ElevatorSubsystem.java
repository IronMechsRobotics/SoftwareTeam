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
	Spark motor1;
	Spark motor2;

	public ElevatorSubsystem() {
		double maxPeriod = .1;
		int minRate = 10;
		int samplesToAverage = 7;
		cim = new Encoder(RobotMap.ELEVATORENCODERA, RobotMap.ELEVATORENCODERB);
		cim.setDistancePerPulse(5);
		cim.setSamplesToAverage(7);
		cim.setMaxPeriod(maxPeriod);
		cim.setMinRate(minRate);
		motor1 = new Spark(RobotMap.ELEVATORMOTORRIGHT);
		motor2 = new Spark(RobotMap.ELEVATORMOTORLEFT);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setMotor(double speed) {
		motor1.set(speed);
		motor2.set(-1*speed);
	}

	public void stopMotor() {
		motor1.set(0);
		motor2.set(0);
	}

	public double getSpeed() {
		return cim.getRate();
	}
}
