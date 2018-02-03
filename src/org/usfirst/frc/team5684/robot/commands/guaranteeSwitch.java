package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GuaranteeSwitch extends CommandGroup {

	private static final double feet = Math.PI * 6;
	private static final double inches = feet / 12.0;
	private static final int turnRight = -90;
	private static final int turnLeft = 90;
	private static final int distanceToSideDrop = 60;
	private static final int distanceToPassSwitch = 108;
	private static final int driveAlongSwitch = 144;

	public GuaranteeSwitch() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		String switchLocation = gameData.substring(0, 1);
		System.out.println("SwitchLocatoin: " + switchLocation);
		boolean isBlue = Robot.getIsBlue();
		boolean isRed = !isBlue;
		boolean imLeft = Robot.getIsLeft();
		boolean imRight = !imLeft;
		boolean switchRight = switchLocation.equals("R");
		boolean switchLeft = !switchRight;
		boolean bothRight = imRight && switchRight;
		boolean bothLeft = imLeft && switchLeft;
		boolean matchingSide = bothRight || bothLeft;
		int amountToTurn = imLeft ? 90 : -90;
		if (matchingSide)
			// drive sort
			this.addSequential(new driveByDistance(distanceToSideDrop));
		else
			// drive long (past switch)
			this.addSequential(new driveByDistance(distanceToPassSwitch));
		// Turn
		this.addSequential(new TurnDegrees(amountToTurn));
		if (!matchingSide)
		// drive horizontal across field
		{
			this.addSequential(new driveByDistance(driveAlongSwitch));
			this.addSequential(new TurnDegrees(amountToTurn));
		}
		// TODO create DriveTillStopped
		this.addSequential(new driveByDistance(1 * feet));

		// Drop Cube
		// TODO create drop cube
		this.addSequential(new DropCube());
	}
}
