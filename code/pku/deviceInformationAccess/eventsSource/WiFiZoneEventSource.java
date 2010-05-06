package pku.deviceInformationAccess.eventsSource;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pku.deviceInformationAccess.eventListener.TerminalEventListener;
import pku.deviceInformationAccess.eventListener.TerminalDepartEventListener;
import pku.deviceInformationAccess.events.DIAEvents;
import pku.deviceInformationAccess.events.TerminalDepartEvent;
import pku.deviceInformationAccess.events.TerminalDetectedEvent;
import pku.deviceInformationAccess.location.Zone;
import pku.deviceInformationAccess.locationProvider.LocationProvider;
import pku.deviceInformationAccess.terminal.Terminal;

public class WiFiZoneEventSource implements ZoneEventSource, Runnable
{
	private LocationProvider locationProvider;
	private Zone zone;
	private List<TerminalEventListener> listeners = new LinkedList<TerminalEventListener>();
	private Thread thread; 
	private boolean isStop = false;
	public WiFiZoneEventSource(LocationProvider locationProvider, Zone zone)
	{
		super();
		this.locationProvider = locationProvider;
		this.zone = zone;
		thread = new Thread(this);
	}

	public void activateEventSource()
	{
		isStop = false;
		thread.start();
	}
	synchronized void notify(List<DIAEvents> leo)
	{
		for(int i = 0;i<listeners.size();i++)
			for(int j = 0;j<leo.size();j++)
			{
				listeners.get(i).update(leo.get(j));
			}
	}
	public synchronized void addListener(TerminalEventListener e)
	{
		listeners.add(e);
	}

	public synchronized void removeListener(TerminalEventListener e)
	{
		listeners.remove(e);

	}

	public void stop()
	{
		// TODO Auto-generated method stub
		isStop = true;

	}

	public void run()
	{
		Set<Terminal> newSet = new HashSet<Terminal>();
		Set<Terminal> lastSet = new HashSet<Terminal>();
		List<DIAEvents> leo = new ArrayList<DIAEvents>();
		while(!isStop)
		{
			leo.clear();
			newSet = locationProvider.getAllTerminals(zone);
			//System.out.println("*********"+lastSet.size());
			Iterator it = newSet.iterator();
			while (it.hasNext())
			{
				Terminal t = (Terminal) it.next();
				Iterator it2 = lastSet.iterator();
				int i =0;
				while(it2.hasNext())
				{
					Terminal t2 = (Terminal) it2.next();
					System.out.println("i="+i);
					if(t2.equals(t))
						break;
					else
						i++;
				}
				/*System.out.println("i="+i);
				System.out.println("lastSet="+lastSet.size());
				System.out.println(i==lastSet.size());*/
				if (i==lastSet.size())
				{
					//System.out.println("dsadasdsadewqewqewqedsadsads*************88");
					System.out.println("new detect event!!!!!!!!!!!");
					TerminalDetectedEvent terminalsDetectedEvent = new TerminalDetectedEvent(this.zone,t);
					leo.add(terminalsDetectedEvent);
				}
			}
			it = lastSet.iterator();
			while (it.hasNext())
			{
				Terminal t = (Terminal) it.next();
				Iterator it2 = newSet.iterator();
				int i =0;
				while(it2.hasNext())
				{
					Terminal t2 = (Terminal) it2.next();
					if(t2.equals(t))
						break;
					else
						i++;
				}
				if (i==newSet.size())
				{
					System.out.println("new depart event!!!!!!!!!!!");
					TerminalDepartEvent terminalsDepartEvent = new TerminalDepartEvent(this.zone,t);
					leo.add(terminalsDepartEvent);
				}
			}
			notify(leo);
			lastSet = newSet;
			try
			{
				Thread.sleep(2*1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		
	}

}
