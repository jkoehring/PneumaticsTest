/*
 * The Piston class is for getting and opening different pistons on the Robot.
 */

package org.usfirst.frc.team1165.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is a generic piston subsystem.
 */
public class Piston extends Subsystem
{
	private final Solenoid solenoidExtend;
	private final Solenoid solenoidRetract;

	public Piston(int solenoidRetractPort, int solenoidExtendPort)
	{
		this.solenoidRetract = solenoidRetractPort >= 0 ? new Solenoid(solenoidRetractPort) : null;
		this.solenoidExtend = solenoidExtendPort >= 0 ? new Solenoid(solenoidExtendPort) : null;
	}

	public void extend()
	{
		set(solenoidRetract, false);
		set(solenoidExtend, true);
	}

	public void retract()
	{
		set(solenoidRetract, true);
		set(solenoidExtend, false);
	}

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
