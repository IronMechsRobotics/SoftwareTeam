package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.ElevatorDown;
import org.usfirst.frc.team5684.robot.commands.ElevatorToHeight;
import org.usfirst.frc.team5684.robot.commands.ElevatorUp;
import org.usfirst.frc.team5684.robot.commands.IntakeCube;
import org.usfirst.frc.team5684.robot.commands.LowerArm;
import org.usfirst.frc.team5684.robot.commands.OutputCube;
import org.usfirst.frc.team5684.robot.commands.RaiseArm;
<<<<<<< HEAD
import org.usfirst.frc.team5684.robot.commands.RecalibrateElevator;
<<<<<<< HEAD
=======
import org.usfirst.frc.team5684.robot.commands.Turn;
>>>>>>> 1b3449fd1c7d7b990530d66289d9261b7d1957d5
=======
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/
import org.usfirst.frc.team5684.robot.commands.Climb;
import org.usfirst.frc.team5684.robot.commands.DriveStraightWithGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class IO {

	public static final Joystick leftStick = new Joystick(0);
	public static final Joystick rightStick = new Joystick(1);
	public static final Joystick xbox = new Joystick(2);
	Button rightFire = new JoystickButton(rightStick, 1);
	Button leftFire = new JoystickButton(leftStick, 1);
	Button raiseArm = new JoystickButton(rightStick, 5);
	Button lowerArm = new JoystickButton(rightStick, 6);
	Button cubeIntake = new JoystickButton(rightStick, 3);
	Button cubeOutput = new JoystickButton(rightStick, 4);
	Button climb = new JoystickButton(leftStick, 2);
<<<<<<< HEAD
<<<<<<< HEAD
	Button support5 = new JoystickButton(supportStick, 5);
	Button support6 = new JoystickButton(supportStick, 6);
	Button support7= new JoystickButton(supportStick, 7);
	Button support8= new JoystickButton(supportStick, 8);
	Button support9= new JoystickButton(supportStick, 9);
	Button support10= new JoystickButton(supportStick, 10);
	Button support11= new JoystickButton(supportStick, 11);
	Button support12= new JoystickButton(supportStick, 12);
	Button supportFire= new JoystickButton(supportStick, 1);
	Button support2= new JoystickButton(supportStick, 2);
	
=======
=======
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/
	Button yButton = new JoystickButton(xbox, 4);
	Button xButton = new JoystickButton(xbox, 2);
	Button aButton = new JoystickButton(xbox, 1);
	Button bButton = new JoystickButton(xbox, 3);
	Button leftBumper = new JoystickButton(xbox, 5);
	Button rightBumper = new JoystickButton(xbox, 6);
	Button rightTrigger = new RightTrigger(xbox);
	Button leftTrigger = new LeftTrigger(xbox);
<<<<<<< HEAD
	Button rightThumbPad = new JoystickButton(xbox, 10);
	
	Button rad1 = new JoystickButton(rightStick, 11);
	Button rad2 = new JoystickButton(rightStick, 12);
>>>>>>> 1b3449fd1c7d7b990530d66289d9261b7d1957d5
=======
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/

	public IO() {
		rad1.whenPressed(new DriveStraightWithGyro(8*4));
		rad2.whenPressed(new Turn(-360));
		leftFire.whileHeld(new ElevatorDown());
		rightFire.whileHeld(new ElevatorUp());
		yButton.whenPressed(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
		aButton.whenPressed(new ElevatorToHeight(5.5));
		bButton.whenPressed(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		xButton.whenPressed(new ElevatorToHeight(RobotMap.MIDDLESCALEHEIGHT));
		raiseArm.whileHeld(new LowerArm());
		lowerArm.whileHeld(new RaiseArm());
		climb.whileHeld(new Climb());
<<<<<<< HEAD
<<<<<<< HEAD
		support5.whileHeld(new RaiseArm());
		support6.whileHeld(new LowerArm());
		support11.whenPressed(new ElevatorToHeight(5.5));
		support9.whenPressed(new ElevatorToHeight(RobotMap.SWITCHHEIGHT));
		support8.whenPressed(new ElevatorToHeight(RobotMap.HIGHSCALEHEIGHT));
		support10.whenPressed(new ElevatorToHeight(RobotMap.MIDDLESCALEHEIGHT));
		support12.whenPressed(new ElevatorToHeight(RobotMap.LOWSCALEHEIGHT));
		support7.whenPressed(new RecalibrateElevator());
		supportFire.whileHeld(new ElevatorDown());
		support2.whileHeld(new IntakeOuttakeCube());
=======
=======
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/
		leftBumper.whileHeld(new IntakeCube());
		rightBumper.whileHeld(new OutputCube());
		rightTrigger.whileHeld(new RaiseArm());
		leftTrigger.whileHeld(new LowerArm());
<<<<<<< HEAD
		rightThumbPad.whenPressed(new RecalibrateElevator());
		
>>>>>>> 1b3449fd1c7d7b990530d66289d9261b7d1957d5
=======
		
>>>>>>> parent of 2468ed5... Added control for the cube intake based on the support joystick/
	}

}