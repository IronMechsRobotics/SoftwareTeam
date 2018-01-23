package org.usfirst.frc.team5684.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5684.robot.RobotMap;
import org.usfirst.frc.team5684.robot.commands.DriveWithTwoJoysticks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private static RobotDrive drive;
	private double deadZone;
	private double positiveM;
	private double positiveB;
	private double negativeM;
	private double negativeB;
	
	
	public DriveTrain()
	{
		drive = new RobotDrive(RobotMap.leftWheels, RobotMap.rightWheels);
		deadZone=.1;
		positiveM=((1-0)/(1-deadZone));
		positiveB=(1-positiveM);
		negativeM=((-1-0)/(-1+deadZone));
		negativeB=(1-negativeM);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithTwoJoysticks());
		drive.setSafetyEnabled(false);
    }
    
    public void drive(Joystick leftStick, Joystick rightStick)
    {
    	
			double forwardMovement =leftStick.getY();
			double turnMovement=rightStick.getX();
			if(Math.abs(forwardMovement)>deadZone)
			{
				if(forwardMovement<0)
				{
					forwardMovement=forwardMovement*negativeM+negativeB;
				}
				else
				{
					forwardMovement=forwardMovement*positiveM+positiveB;
				}
			}
			else
			{
				forwardMovement=0;
			}
			if(Math.abs(turnMovement)>deadZone)
			{
				
			}
			else
			{	
				turnMovement=0;
			}	
			drive.arcadeDrive(forwardMovement, turnMovement);
			
    }
}

