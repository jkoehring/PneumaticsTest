/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.subsystems.Piston;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Common base class for all commands that operate a piston.
 * 
 * The timeout is used to determine how long the solenoid should
 * be activated. Subclasses should NOT set or change the timeout.
 */
public abstract class PistonCommand extends Command
{
    /** Piston subsystems */
    public static Piston piston01 = new Piston(0, 1);
       
    private final boolean isExtending;
    private final Piston piston;
	
	final static boolean extend = true;
	final static boolean retract = false;
	
	final static double powerOnTime = 0.05; // 50 milliseconds
    
    public PistonCommand(Piston piston, boolean isExtending)
	{
		super(powerOnTime);
		requires(piston);
        this.piston = piston;
        this.isExtending = isExtending;
    }

    protected void initialize() 
    {
        if (isExtending) 
        {
            piston.extend();
        }
		else 
        {
            piston.retract();
        }
    }

    protected void execute() 
    {
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
		idle();
    }

    protected void interrupted() 
    {
		idle();
    }
    
	private void idle()
	{
		// TODO: Uncomment if want to idle the piston when the command is finished or interrupted.
		piston.idle();
	}
}
