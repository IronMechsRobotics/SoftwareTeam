package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchLR extends CommandGroup {

	public AutoSwitchLR() {
		addSequential(new DriveStraightWithGyro(196 + 12));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
		System.out.println("hello");
		addSequential(new Turn(-90));
		addSequential(new DriveStraightWithGyro(13 * 12));
		addSequential(new Turn(-90));
		addSequential(new Wait(), .5);
		addSequential(new DriveStraightWithGyro(6));
		addSequential(new OutputCube(), 3);
	}
}
