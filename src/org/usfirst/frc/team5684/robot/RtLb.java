package org.usfirst.frc.team5684.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class RtLb extends Button {

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return Robot.io.getJoystick(2).getRawAxis(3) > 0 && Robot.io.getJoystick(2).getRawButton(4);
	}

}