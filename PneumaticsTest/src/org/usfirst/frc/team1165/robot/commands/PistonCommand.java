/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.subsystems.Piston;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Common base class for all commands that operate a piston.
 * 
 * The timeout is used to determine how long the solenoid should
 * be activated. Subclasses should NOT set or change the timeout.
 */
public abstract class PistonCommand extends Command implements Runnable
{
    /** Piston subsystems */
    public static Piston piston01 = new Piston(0, 1);
       
    private final boolean isExtending;
    private final Piston piston;
	
	final static boolean extend = true;
	final static boolean retract = false;
	
	private double powerOnTime = 0.005; // seconds
	
	private boolean isFinished;
    
    public PistonCommand(Piston piston, boolean isExtending, double powerOnTime)
	{
		requires(piston);
        this.piston = piston;
        this.isExtending = isExtending;
        this.powerOnTime = powerOnTime;
    }

    protected void initialize() 
    {
    	isFinished = false;
    	new Thread(this).start();
    }

    protected void execute() 
    {
    }

    protected boolean isFinished() 
    {
        return isFinished;
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
		piston.idle();
	}
	
	public void run()
	{
        if (isExtending) 
        {
            piston.extend();
        }
		else 
        {
            piston.retract();
        }
        
        try
		{
			Thread.sleep((long)(powerOnTime * 1000));
		}
		catch (InterruptedException e)
		{
		}
        
        idle();

        isFinished = true;
	}
}
