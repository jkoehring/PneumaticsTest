package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

public class ExtendPiston2 extends PistonCommand
{
	public ExtendPiston2()
	{
		super(piston2, extend, RobotMap.piston2EnergizeTimeKey);
	}
}
