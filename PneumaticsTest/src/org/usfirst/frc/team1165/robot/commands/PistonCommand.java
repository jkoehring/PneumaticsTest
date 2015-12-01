/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.subsystems.Piston;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Common base class for all commands that operate a piston.
 * 
 * The energizeTime is used to determine how long a solenoid
 * should be activated.
 */
public abstract class PistonCommand extends Command
{
    /** Piston subsystems */
    public static Piston piston01 = new Piston(0, 1);
    public static Piston piston2 = new Piston(-1, 2);
       
    private final boolean isExtending;
    private final Piston piston;
	
	final static boolean extend = true;
	final static boolean retract = false;
	
	// Indicates how long a solenoid will be energized
	private double energizeTime = 0.05; // seconds
	
	// Set this for getting energizeTime from SmartDasboard
	private String energizeTimeKey;
	
	private boolean isFinished;
	
	/**
	 * Constructor that uses a default energize time.
	 */
	private PistonCommand(Piston piston, boolean isExtending)
	{
		requires(piston);
        this.piston = piston;
        this.isExtending = isExtending;
	}
    
	/**
	 * Constructor that uses a fixed energize time.
	 */
    public PistonCommand(Piston piston, boolean isExtending, double energizeTime)
	{
    	this(piston, isExtending);
        this.energizeTime = energizeTime;
        this.energizeTimeKey = null;
    }

    /**
     * Constructor that uses an energize time from the SmartDashboard.
     */
    public PistonCommand(Piston piston, boolean isExtending, String energizeTimeKey)
	{
    	this(piston, isExtending);
        this.energizeTimeKey = energizeTimeKey;
    }

    // This method is called once just before a command is started.
    protected void initialize() 
    {
    	// See if should get time to energize solenoid from SmartDashboard.
    	if (energizeTimeKey != null)
    	{
    		energizeTime = SmartDashboard.getNumber(energizeTimeKey);
    	}
    	
    	isFinished = false;
    	new Thread(new Runnable()
		{
    		@Override
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
    	        
    	       	Timer.delay(energizeTime);
    	        
    	        idle();

    	        isFinished = true;
    		}
		}).start();
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
}
