package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

public class ExtendPiston01 extends PistonCommand
{
	public ExtendPiston01()
	{
		super(piston01, extend, RobotMap.piston01EnergizeTimeKey);
	}
}
