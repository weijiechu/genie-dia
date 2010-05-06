package pku.deviceInformationAccess.locationProvider;

import java.util.List;
import java.util.Set;

import pku.deviceInformationAccess.location.Location;
import pku.deviceInformationAccess.location.StayInZone;
import pku.deviceInformationAccess.location.Zone;
import pku.deviceInformationAccess.terminal.Terminal;

public interface LocationProvider
{
	public Location getLocation( Terminal terminal );
	public Set<Terminal> getAllTerminals(Zone zoneInfo);
	public Set<Zone> getAllZone();
	public Zone getZone(int id);
	public List<StayInZone> getTerminalPath(Terminal terminal);
	public Set<Terminal> getTerminalByName(String name);
}
