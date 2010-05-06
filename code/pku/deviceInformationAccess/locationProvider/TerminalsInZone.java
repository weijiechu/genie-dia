package pku.deviceInformationAccess.locationProvider;

import java.util.HashSet;
import java.util.Set;

import pku.deviceInformationAccess.terminal.Terminal;

class TerminalsInZone
{
	Set<Terminal> terminals;
	int batchNum;
	TerminalsInZone()
	{
		super();
		// TODO Auto-generated constructor stub
		terminals = new HashSet<Terminal>();
		batchNum = -1;
	}

}
