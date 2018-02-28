package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoInsideDumpCube extends CommandGroup {

    public AutoInsideDumpCube() {
		addSequential(new DriveStraightWithGyro(8 * RobotMap.FEET + 6 * RobotMap.INCHES));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.3);
		addSequential(new OutputCube(), 3);
    }
}
