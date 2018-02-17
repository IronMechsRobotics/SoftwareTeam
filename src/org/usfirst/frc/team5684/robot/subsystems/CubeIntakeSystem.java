package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntakeSystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Spark leftSide;
	private Spark rightSide;
	private Victor arm;

	public CubeIntakeSystem() {
		leftSide = new Spark(RobotMap.LEFTCUBEMOTOR);
		rightSide = new Spark(RobotMap.RIGHTCUBEMOTOR);
		arm = new Victor(RobotMap.COLLECTORRASIEMOTOR);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setArmMotor(double speed)
	{
		arm.set(speed);
	}

	public void setMotors(double speed) {
		leftSide.set(speed * -1);
		rightSide.set(speed);
	}
}
