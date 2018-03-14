package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftScale extends CommandGroup {

    public AutoLeftScale() {
    	addSequential(new DriveStraightWithGyro(RobotMap.DISTANCETOSCALE));
    	addSequential(new Turn(RobotMap.TURNRIGHT));
    	addSequential(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
    	addSequential(new LowerArm(), 1);
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
		addSequential(new OutputCube(.5), 1);
		addSequential(new DriveStraightWithGyro(-8 * RobotMap.INCHES));
		addSequential(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new Turn(RobotMap.TURNRIGHT));
		addSequential(new DriveStraightWithGyro(8 * RobotMap.INCHES));
    }
}
