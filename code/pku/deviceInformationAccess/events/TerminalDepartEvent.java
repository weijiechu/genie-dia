package pku.deviceInformationAccess.events;

import java.util.EventObject;

import pku.deviceInformationAccess.location.Zone;
import pku.deviceInformationAccess.terminal.Terminal;

public class TerminalDepartEvent extends DIAEvents
{
	Terminal terminal;
	Zone zone;
	public TerminalDepartEvent(Zone zone, 
			Terminal terminal)
	{
		this.terminal = terminal;
		this.zone = zone;
	}
	public Terminal getTerminal()
	{
		return terminal;
	}
	/**
	 * @return the zone
	 */
	public Zone getZone()
	{
		return zone;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3297566772072947771L;

}
