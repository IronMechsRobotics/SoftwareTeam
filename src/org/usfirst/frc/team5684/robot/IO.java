package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.ElevatorDown;
import org.usfirst.frc.team5684.robot.commands.ElevatorUp;
import org.usfirst.frc.team5684.robot.commands.IntakeCube;
import org.usfirst.frc.team5684.robot.commands.driveByDistance;
import org.usfirst.frc.team5684.robot.commands.testDrivetrain;
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
	Button radTest2 = new JoystickButton(rightStick, 12);

	public IO() {
		radTest.whenPressed(new driveByDistance(10 * Math.PI * 6));
		radTest2.toggleWhenPressed(new testDrivetrain());
		rightFire.whileHeld(new ElevatorDown());
		leftFire.whileHeld(new ElevatorUp());
	}

}
