package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoForSwitch extends CommandGroup {

	private static final int FEET = 12;
	private static final int INCHES = 1;
	private static final int turnRight = -90;
	private static final int turnLeft = 90;
	private static final int distanceToSideDrop = 160 * INCHES;
	private static final int distanceToPassSwitch = 196 * INCHES + 1 * FEET;
	private static final int driveAlongSwitch = 13 * FEET;

	public GoForSwitch(String gameData, boolean isRight) {
		String side = isRight ? "R" : "L";
		String scaleLocation = gameData.substring(1, 2);
		String switchLocation = gameData.substring(0, 1);

		boolean imLeft = Robot.getIsLeft();
		boolean imRight = !imLeft;
		boolean switchRight = switchLocation.equalsIgnoreCase("R");
		boolean switchLeft = !switchRight;
		boolean bothRight = imRight && switchRight;
		boolean bothLeft = imLeft && switchLeft;
		boolean matchingSide = bothRight || bothLeft;

		int amountToTurn = imLeft ? 90 : -90;
		System.out.println("switchLocation\t" + switchLocation);
		System.out.println("scaleLocation\t" + scaleLocation);
		System.out.println("matchingSide\t" + matchingSide);
		System.out.println("GameData\t" + gameData);

		System.out.println("Driving forward 0");
		addSequential(new DriveStraightWithGyro(distanceToSideDrop));
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);

		// if have gameData
		if (gameData.length() >= 3) {
			if (!matchingSide) // drive long (past switch)
			{
				System.out.println("NOT MATCHING SIDE0");
				this.addSequential(new DriveStraightWithGyro(distanceToPassSwitch - distanceToSideDrop));
			}
			System.out.println("TURNING 1");
			this.addSequential(new Turn(amountToTurn));// Turn
			if (!matchingSide) {// drive horizontal across field
				System.out.println("NOT MATCHING SIDE1");
				this.addSequential(new DriveStraightWithGyro(driveAlongSwitch));
				System.out.println("TURNING 2");
				this.addSequential(new Turn(amountToTurn));
			}
			// TODO create DriveTillStopped
			System.out.println("Driving forward1");
			this.addSequential(new DriveStraightWithGyro(1 * FEET));

			// Drop Cube
			// TODO create drop cube
			this.addSequential(new DropCube());
		} else {
			// we didn't get any game data.
			return;
			// TODO write to the log
		}
	}

}
