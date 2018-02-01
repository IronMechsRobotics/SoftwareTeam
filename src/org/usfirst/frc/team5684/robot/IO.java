package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.driveByDistance;
import org.usfirst.frc.team5684.robot.commands.testVelPID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class IO {

	public final Joystick leftStick = new Joystick(0);
	public final Joystick rightStick = new Joystick(1);
	Button rightFire = new JoystickButton(rightStick, 1);
	Button leftFire = new JoystickButton(leftStick, 1);
	Button radTest = new JoystickButton(rightStick, 11);

	public IO() {
		radTest.toggleWhenPressed(new testVelPID());
	}

}
