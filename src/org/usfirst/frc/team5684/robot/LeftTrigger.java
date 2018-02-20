package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class LeftTrigger extends Button {
	Joystick joystick;
	public LeftTrigger(Joystick joystick)
	{
		this.joystick=joystick;
	}
    public boolean get() {
    	return joystick.getRawAxis(RobotMap.leftTrigger)>0;
    }
}
