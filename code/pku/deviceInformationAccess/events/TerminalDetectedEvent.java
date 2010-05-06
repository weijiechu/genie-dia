package pku.deviceInformationAccess.events;

import java.util.EventObject;

import pku.deviceInformationAccess.location.Zone;
import pku.deviceInformationAccess.terminal.Terminal;

public class TerminalDetectedEvent extends DIAEvents
{

	Terminal terminal;
	Zone zone;
	/**
	 * @return the terminal
	 */
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


	public TerminalDetectedEvent(Zone zone, 
			Terminal terminal)
	{
		this.zone = zone;
		this.terminal = terminal;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
