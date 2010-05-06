package pku.deviceInformationAccess.eventListener;

import java.util.EventListener;
import java.util.EventObject;

import pku.deviceInformationAccess.events.DIAEvents;

public interface TerminalEventListener extends EventListener
{
	void update(DIAEvents e);
}
