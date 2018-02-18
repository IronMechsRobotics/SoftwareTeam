package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;
import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuto extends CommandGroup {

	public SimpleAuto() {
		addParallel(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		addSequential(new DriveStraightWithGyro(160));
		String gameData = "";
		while (gameData.length() < 3) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		String switchLocation = gameData.substring(0, 1);
		String scaleLocation = gameData.substring(0, 2);
		boolean isRight = Robot.getIsRight();
		String side = isRight ? "R" : "L";
		boolean switchMatch = switchLocation.equalsIgnoreCase(side);
		boolean scaleMatch = scaleLocation.equalsIgnoreCase(side);
		if (!switchMatch && scaleMatch) {
			addSequential(new DriveStraightWithGyro(400));
		}
		if (isRight) {
			addSequential(new Turn(90));
		} else {
			addSequential(new Turn(-90));
		}
		addSequential(new OutputCube(), 5);

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
