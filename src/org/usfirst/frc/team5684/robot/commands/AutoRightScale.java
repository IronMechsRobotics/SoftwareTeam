package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSCALE));
    	addSequential(new Turn(RobotMap.TURNLEFT));
    	addSequential(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
    	addSequential(new LowerArm(), 1);
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
		addSequential(new OutputCube(.5), 1);
		addSequential(new DriveStraightWithGyro(-8 * RobotMap.INCHES));
		addSequential(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new Turn(RobotMap.TURNLEFT));
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
    }
}
