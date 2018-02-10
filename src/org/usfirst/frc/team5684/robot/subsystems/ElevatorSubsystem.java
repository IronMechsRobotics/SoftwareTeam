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
		cim = new Encoder(RobotMap.ELEVATORENCODERA, RobotMap.ELEVATORENCODERB, true, Encoder.EncodingType.k4X);
		double ticksPerMotorRev = 20.0 / 1;
		//ticksPerMotorRev means 20 ticks per 1 revolution of the motor
		double motorRevPerDrum = 1.0 / (1.0 / 16);
		//for every time the motor goes around 16 times the drum spins once
		double drumPerInch = 1 / (2.0 * Math.PI);
		//for every time the drum spins once the elevator rises 2Pi inches
		double inchesPerMotor = motorRevPerDrum*drumPerInch;
		double distane = ticksPerMotorRev * motorRevPerDrum * drumPerInch;
		cim.setDistancePerPulse(1.0 / distane);
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
		motor2.set(-1 * speed);
	}

	public void stopMotor() {
		motor1.set(0);
		motor2.set(0);
	}

	public double getSpeed() {
		System.out.println(cim.getRaw());
		return cim.getRate();
	}
}
