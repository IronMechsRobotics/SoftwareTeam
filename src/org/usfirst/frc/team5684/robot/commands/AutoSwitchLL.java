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
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSIDEDROP));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), RobotMap.LOWERARMTIME);
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
		addSequential(new OutputCube(.5), 3);
	}
}
