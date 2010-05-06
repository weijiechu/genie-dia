package pku.deviceInformationAccess.eventsSource;

import java.util.EventListener;

import pku.deviceInformationAccess.eventListener.TerminalEventListener;

public interface ZoneEventSource
{
	void activateEventSource();
	void addListener(TerminalEventListener e);
	void removeListener(TerminalEventListener e);
	void stop();
}
