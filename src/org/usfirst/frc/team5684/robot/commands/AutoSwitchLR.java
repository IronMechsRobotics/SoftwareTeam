package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchLR extends CommandGroup {

	public AutoSwitchLR() {
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOPASSSWITCH));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(RobotMap.DRIVEALONGSWITCH));
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(6));
		addSequential(new OutputCube(), 3);
	}
}