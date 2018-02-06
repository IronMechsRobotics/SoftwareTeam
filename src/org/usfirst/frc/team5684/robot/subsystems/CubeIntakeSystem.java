package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntakeSystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Spark leftSide;
	private Spark rightSide;

	public CubeIntakeSystem() {
		leftSide = new Spark(RobotMap.leftSideCubeIntake);
		rightSide = new Spark(RobotMap.rightSideCubeIntake);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setMotors(double speed) {
		
		leftSide.set(speed);
		rightSide.set(speed);
	}
}
