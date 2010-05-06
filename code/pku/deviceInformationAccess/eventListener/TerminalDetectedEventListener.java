package pku.deviceInformationAccess.eventListener;

import java.util.EventObject;

import pku.deviceInformationAccess.events.DIAEvents;
import pku.deviceInformationAccess.events.TerminalDepartEvent;
import pku.deviceInformationAccess.events.TerminalDetectedEvent;
import pku.deviceInformationAccess.terminal.Bluetooth;
import pku.deviceInformationAccess.terminal.Terminal;
import pku.deviceInformationAccess.terminal.WiFiEquipment;

public class TerminalDetectedEventListener implements TerminalEventListener
{
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TerminalDetectedEventListener))
			return false;
		return true;
	}
	public void update(DIAEvents e)
	{
		if(e instanceof TerminalDetectedEvent)
		{
			TerminalDetectedEvent tde = (TerminalDetectedEvent)e;
			Terminal we = (Terminal) tde.getTerminal();
			System.out.println("*------------------Detected--------------------------*");
			System.out.println(tde.getZone().getZoneDescription()+": the "+we.getFriendlyName()+" Detected");
			System.out.println("*---------------------------------------------------*");
		}
	}


}
