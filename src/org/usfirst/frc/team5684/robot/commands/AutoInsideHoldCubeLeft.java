package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoInsideHoldCubeLeft extends CommandGroup {

    public AutoInsideHoldCubeLeft() {
    	addSequential(new DriveStraightWithGyro(8 * RobotMap.FEET + 6 * RobotMap.INCHES));
		addSequential(new DriveStraightWithGyro(-6.0*RobotMap.INCHES));
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOPASSSWITCH/2));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(6));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new LowerArm(), 1.25);
    }
}
