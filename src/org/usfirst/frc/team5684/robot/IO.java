package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.ElevatorDown;
import org.usfirst.frc.team5684.robot.commands.ElevatorToHeight;
import org.usfirst.frc.team5684.robot.commands.ElevatorUp;
import org.usfirst.frc.team5684.robot.commands.FlipAndlower;
import org.usfirst.frc.team5684.robot.commands.IntakeCube;
import org.usfirst.frc.team5684.robot.commands.OutputCube;
import org.usfirst.frc.team5684.robot.commands.Turn;
import org.usfirst.frc.team5684.robot.commands.driveByDistance;
import org.usfirst.frc.team5684.robot.commands.testDrivetrain;
import org.usfirst.frc.team5684.robot.commands.testVelPID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class IO {

	public static final Joystick leftStick = new Joystick(0);
	public static final Joystick rightStick = new Joystick(1);
	public static final Joystick xbox = new Joystick(2);
	Button rightFire = new JoystickButton(rightStick, 1);
	Button leftFire = new JoystickButton(leftStick, 1);
	Button radTest = new JoystickButton(rightStick, 11);
	Button radTest2 = new JoystickButton(rightStick, 12);
	Button cubeIntake = new JoystickButton(rightStick, 3);
	Button cubeOutput = new JoystickButton(rightStick, 4);
	Button rightTwo = new JoystickButton(rightStick, 2);
	Button xboxFour = new JoystickButton(xbox, 4);
	Button xboxTwo = new JoystickButton(xbox, 2);
	Button xboxOne = new JoystickButton(xbox, 1);
	Button xboxThree = new JoystickButton(xbox, 3);
	Button rtLb = new RtLb();
	Button ltRb = new LtRb();

	public IO() {
		radTest.whenPressed(new Turn(90));
		radTest2.toggleWhenPressed(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		leftFire.whileHeld(new ElevatorDown());
		rightFire.whileHeld(new ElevatorUp());
		cubeIntake.whileHeld(new IntakeCube());
		cubeOutput.whileHeld(new OutputCube());
		rightTwo.whenPressed(new FlipAndlower());
		xboxThree.whenPressed(new ElevatorUp());
		xboxFour.whenPressed(new ElevatorUp());
		xboxOne.whenPressed(new ElevatorUp());
		xboxTwo.whenPressed(new ElevatorUp());
		rtLb.whenPressed(new OutputCube());
		ltRb.whenPressed(new IntakeCube());
	}

	public Joystick getJoystick(int i) {
		if (i == 0) {
			return leftStick;
		} else if (i == 1) {
			return rightStick;
		} else if (i == 2) {
			return xbox;
		} else {
			return null;
		}
	}
}