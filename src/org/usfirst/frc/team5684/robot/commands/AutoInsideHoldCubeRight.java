package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoInsideHoldCubeRight extends CommandGroup {

    public AutoInsideHoldCubeRight() {
    	addSequential(new DriveStraightWithGyro(8 * RobotMap.FEET + 6 * RobotMap.INCHES));
		addSequential(new DriveStraightWithGyro(-4 * RobotMap.FEET));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOPASSSWITCH/2));
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(4 * RobotMap.FEET));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new LowerArm(), 1.25);
    }
}
