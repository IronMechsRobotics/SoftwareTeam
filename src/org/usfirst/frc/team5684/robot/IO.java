package org.usfirst.frc.team5684.robot;

import org.usfirst.frc.team5684.robot.commands.ElevatorDown;
import org.usfirst.frc.team5684.robot.commands.ElevatorToHeight;
import org.usfirst.frc.team5684.robot.commands.ElevatorUp;
import org.usfirst.frc.team5684.robot.commands.IntakeCube;
import org.usfirst.frc.team5684.robot.commands.IntakeOuttakeCube;
import org.usfirst.frc.team5684.robot.commands.LowerArm;
import org.usfirst.frc.team5684.robot.commands.OutputCube;
import org.usfirst.frc.team5684.robot.commands.RaiseArm;
import org.usfirst.frc.team5684.robot.commands.RecalibrateElevator;
import org.usfirst.frc.team5684.robot.commands.Turn;
import org.usfirst.frc.team5684.robot.commands.Climb;
import org.usfirst.frc.team5684.robot.commands.DriveStraightWithGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class IO {

	public static final Joystick leftStick = new Joystick(0);
	public static final Joystick rightStick = new Joystick(1);
	public static final Joystick supportStick = new Joystick(2);

	Button rightFire = new JoystickButton(rightStick, 1);
	Button leftFire = new JoystickButton(leftStick, 1);
	Button raiseArm = new JoystickButton(rightStick, 5);
	Button lowerArm = new JoystickButton(rightStick, 6);
	Button cubeIntake = new JoystickButton(rightStick, 3);
	Button cubeOutput = new JoystickButton(rightStick, 4);
	Button climb = new JoystickButton(leftStick, 2);
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
	Button support4= new JoystickButton(supportStick, 4);
	Button support3= new JoystickButton(supportStick, 3);
	Button rad= new JoystickButton(rightStick, 11);
	Button rad1= new JoystickButton(rightStick, 12);


	public IO() {

		leftFire.whileHeld(new ElevatorDown());
		rightFire.whileHeld(new ElevatorUp());
		raiseArm.whileHeld(new LowerArm());
		lowerArm.whileHeld(new RaiseArm());
		climb.whileHeld(new Climb());
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
		support4.whenPressed(new ElevatorToHeight(50));
		support3.whileHeld(new OutputCube());
		
		rad.whenPressed(new Turn(90));
		rad1.whenPressed(new Turn(-90));
	}

}