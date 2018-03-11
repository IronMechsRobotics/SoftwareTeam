package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchRL extends CommandGroup {

	public AutoSwitchRL() {
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOPASSSWITCH));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(RobotMap.DRIVEALONGSWITCH));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new Wait(.25));
		// Comment the following 3 lines out
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
		addSequential(new LowerArm(), RobotMap.LOWERARMTIME);
		addSequential(new OutputCube(.5), 3);
	}
}
