package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchLL extends CommandGroup {

	public AutoSwitchLL() {
		addSequential(new DriveStraightWithGyro(160));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
		System.out.println("hello");
		addSequential(new Turn(-90));
		System.out.println("hello");
		addSequential(new DriveStraightWithGyro(4));
		addSequential(new OutputCube(), 3);

	}
}
