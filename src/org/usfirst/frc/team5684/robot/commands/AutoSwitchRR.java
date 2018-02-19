package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchRR extends CommandGroup {

	public AutoSwitchRR() {
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSIDEDROP));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(4));
		addSequential(new OutputCube(), 3);
	}
}
