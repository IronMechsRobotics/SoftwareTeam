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
		addParallel(new LowerArm(), 1.5);
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(RobotMap.DRIVEALONGSWITCH));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new Wait(.25));
		addSequential(new DriveStraightWithGyro(6));
		addSequential(new OutputCube(), 3);
	}
}
