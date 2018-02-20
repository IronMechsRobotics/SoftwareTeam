package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.DriveStraightWithGyro;
import org.usfirst.frc.team5684.robot.commands.ElevatorDown;
import org.usfirst.frc.team5684.robot.commands.ElevatorToHeight;
import org.usfirst.frc.team5684.robot.commands.ElevatorUp;
import org.usfirst.frc.team5684.robot.commands.FlipAndlower;
import org.usfirst.frc.team5684.robot.commands.IntakeCube;
import org.usfirst.frc.team5684.robot.commands.LowerArm;
import org.usfirst.frc.team5684.robot.commands.OutputCube;
import org.usfirst.frc.team5684.robot.commands.RaiseArm;
import org.usfirst.frc.team5684.robot.commands.Turn;
import org.usfirst.frc.team5684.robot.commands.testDrivetrain;
import org.usfirst.frc.team5684.robot.commands.testVelPID;
import org.usfirst.frc.team5684.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class IO {

	public static final Joystick leftStick = new Joystick(0);
	public static final Joystick rightStick = new Joystick(1);
	public static final Joystick xbox = new Joystick(2);
	Button rightFire = new JoystickButton(rightStick, 1);
	Button leftFire = new JoystickButton(leftStick, 1);
	Button radTest1 = new JoystickButton(rightStick, 7);
	Button radTest2 = new JoystickButton(rightStick, 8);
	Button radTest3 = new JoystickButton(rightStick, 9);
	Button radTest4 = new JoystickButton(rightStick, 10);
	Button radTest5 = new JoystickButton(rightStick, 11);
	Button radTest6 = new JoystickButton(rightStick, 12);
	Button raiseArm = new JoystickButton(rightStick, 5);
	Button lowerArm = new JoystickButton(rightStick, 6);
	Button cubeIntake = new JoystickButton(rightStick, 3);
	Button cubeOutput = new JoystickButton(rightStick, 4);
	Button rightTwo = new JoystickButton(rightStick, 2);
	Button climb = new JoystickButton(leftStick, 2);
	Button yButton = new JoystickButton(xbox, 4);
	Button xButton = new JoystickButton(xbox, 2);
	Button aButton = new JoystickButton(xbox, 1);
	Button bButton = new JoystickButton(xbox, 3);
	Button rtLb = new RtLb();
	Button ltRb = new LtRb();
	Button leftBumper = new JoystickButton(xbox, 5);
	Button rightBumper = new JoystickButton(xbox, 6);
	Button rightTrigger = new RightTrigger(xbox);
	Button leftTrigger = new LeftTrigger(xbox);

	public IO() {
		// radTest.whenPressed(new Turn(90));
		radTest1.toggleWhenPressed(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		radTest2.toggleWhenPressed(new ElevatorToHeight(RobotMap.LOWSCALEHEIGHT));
		radTest3.toggleWhenPressed(new ElevatorToHeight(RobotMap.MIDDLESCALEHEIGHT));
		radTest4.toggleWhenPressed(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
		radTest5.toggleWhenPressed(new Turn(90));
		radTest6.toggleWhenPressed(new Turn(-90));
		// radTest6.whenPressed(new DriveStraightWithGyro(12 * 12));
		leftFire.whileHeld(new ElevatorDown());
		rightFire.whileHeld(new ElevatorUp());
		rightTwo.whenPressed(new FlipAndlower());
		yButton.whenPressed(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
		aButton.whenPressed(new ElevatorToHeight(5.5));
		bButton.whenPressed(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		xButton.whenPressed(new ElevatorToHeight(RobotMap.MIDDLESCALEHEIGHT));
		rtLb.whileHeld(new OutputCube());
		ltRb.whileHeld(new IntakeCube());
		raiseArm.whileHeld(new LowerArm());
		lowerArm.whileHeld(new RaiseArm());
		climb.whileHeld(new Climb());
		leftBumper.whileHeld(new IntakeCube());
		rightBumper.whileHeld(new OutputCube());
		rightTrigger.whileHeld(new RaiseArm());
		leftTrigger.whileHeld(new LowerArm());
		
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