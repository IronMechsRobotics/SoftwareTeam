package org.usfirst.frc.team5684.robot.subsystems;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	private double upSpeed = .5;
	public double downSpeed = -.5;
	private Spark arm;
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	arm=new Spark(RobotMap.arm);
    }
    
    public void goUp()
    
    {
    	arm.set(upSpeed);
    }
    
    public void goDown()
    {
    	arm.set(downSpeed);
    	
    }
    public void stop()
    {
    	arm.set(0);
    }
}

