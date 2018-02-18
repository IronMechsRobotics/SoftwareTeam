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

	public GoForSwitch() {
		addSequential(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addParallel(new LowerArm(), 1.5);
		addParallel(new DriveStraightWithGyro(distanceToSideDrop));

		String gameData = getGameData();
		if (gameData.length() >= 3) {
			String switchLocation = gameData.substring(0, 1);
			String scaleLocation = gameData.substring(1, 2);
			boolean isRight = Robot.getIsRight();
			String side = isRight ? "R" : "L";
			boolean switchMatch = switchLocation.equalsIgnoreCase(side);
			boolean scaleMatch = scaleLocation.equalsIgnoreCase(side);
			boolean imLeft = Robot.getIsLeft();
			boolean imRight = !imLeft;
			boolean switchRight = switchLocation.equals("R");
			boolean switchLeft = !switchRight;
			boolean bothRight = imRight && switchRight;
			boolean bothLeft = imLeft && switchLeft;
			boolean matchingSide = bothRight || bothLeft;
			int amountToTurn = imLeft ? 90 : -90;
			if (!matchingSide)
				// drive long (past switch)
				this.addSequential(new DriveStraightWithGyro(distanceToPassSwitch - distanceToSideDrop));
			// Turn
			this.addSequential(new Turn(amountToTurn));
			if (!matchingSide)
			// drive horizontal across field
			{
				this.addSequential(new DriveStraightWithGyro(driveAlongSwitch));
				this.addSequential(new Turn(amountToTurn));
			}
			// TODO create DriveTillStopped
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

	public String getGameData() {
		String gameData = "";
		long stopTime = System.currentTimeMillis() + 3 * 1000;
		// wait 3 seconds if no data return ""
		while (gameData.length() < 3 || System.currentTimeMillis() >= stopTime) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		return gameData;
	}
}
