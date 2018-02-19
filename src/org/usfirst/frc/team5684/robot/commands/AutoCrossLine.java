package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossLine extends CommandGroup {

	public AutoCrossLine() {
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSIDEDROP));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
	}
}
