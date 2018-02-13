package org.usfirst.frc.team5684.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class LtRb extends Button {
	
	public boolean get() {
		return Robot.io.getJoystick(2).getRawAxis(2) > 0 && Robot.io.getJoystick(2).getRawButton(5);
	}
	
}