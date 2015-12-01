package org.usfirst.frc.team1165.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is a generic piston subsystem that supports pistons operated by one or two solenoids.
 */
public class Piston extends Subsystem
{
	private final Solenoid solenoidExtend;
	private final Solenoid solenoidRetract;
	
	/**
	 * Constructor that takes Solenoids.
	 * A solenoid may be null, indicating that is is not used.
	 */
	public Piston(Solenoid solenoidRetract, Solenoid solenoidExtend)
	{
		this.solenoidRetract = solenoidRetract;
		this.solenoidExtend = solenoidExtend;
	}

	/**
	 * Constructor that accepts solenoid port numbers.
	 * A port number may be -1, indicating that it is not used.
	 */
	public Piston(int solenoidRetractPort, int solenoidExtendPort)
	{
		this(
			solenoidRetractPort >= 0 ? new Solenoid(solenoidRetractPort) : null,
			solenoidExtendPort >= 0 ? new Solenoid(solenoidExtendPort) : null);
	}

	/**
	 * Command the solenoid(s) to extend the piston.
	 */
	public void extend()
	{
		set(solenoidRetract, false);
		set(solenoidExtend, true);
	}

	/**
	 * Command the solenoid(s) to retract the piston.
	 */
	public void retract()
	{
		set(solenoidRetract, true);
		set(solenoidExtend, false);
	}

	/**
	 * Command the solenoid(s) to go idle.
	 */
	public void idle()
	{
		set(solenoidRetract, false);
		set(solenoidExtend, false);
	}

	public Solenoid getRetractSolenoid()
	{
		return solenoidRetract;
	}

	public Solenoid getExtendSolenoid()
	{
		return solenoidExtend;
	}

	@Override
	protected void initDefaultCommand()
	{
	}
	
	private void set(Solenoid solenoid, boolean state)
	{
		if (null != solenoid)
		{
			solenoid.set(state);
		}
	}
}
