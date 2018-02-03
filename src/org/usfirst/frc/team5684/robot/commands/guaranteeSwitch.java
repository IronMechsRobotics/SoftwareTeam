package org.usfirst.frc.team5684.robot.commands;

import org.usfirst.frc.team5684.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class guaranteeSwitch extends CommandGroup {
	private static final double  feet=Math.PI*6;
	private static final double inches=feet/12.0;
    public guaranteeSwitch() {
    	boolean isBlue = Robot.getIsBlue();
    	boolean isRed=!isBlue;
    	boolean imLeft=Robot.getIsLeft();    
    	boolean imRight=!imLeft;
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			//Put left auto code here
		} else {
			//Put right auto code here
		}
    	
    	addParallel(new driveByDistance(4*feet));
    	if (imLeft)
    	{
 
    	}
    	else
    	{
    		addParallel(new driveByDistance(10*feet));
    	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
