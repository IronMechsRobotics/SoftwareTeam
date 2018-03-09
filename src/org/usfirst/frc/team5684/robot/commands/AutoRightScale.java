package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSCALE));
		addParallel(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
		addParallel(new LowerArm(), 1);
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
		addSequential(new OutputCube(), 3);
    }
}
