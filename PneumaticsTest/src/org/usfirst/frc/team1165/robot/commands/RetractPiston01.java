package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

public class RetractPiston01 extends PistonCommand
{
	public RetractPiston01()
	{
		super(piston01, retract, RobotMap.piston01EnergizeTimeKey);
	}
}
