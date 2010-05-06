package pku.deviceInformationAccess.terminal;

import pku.deviceInformationAccess.location.Location;

public class Bluetooth extends Terminal
{
	//Location location;
	
/*	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location location)
	{
		this.location = location;
	}*/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bluetooth))
			return false;
		Bluetooth other = (Bluetooth) obj;
		if (mac == null)
		{
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}

	public String getFriendlyName()
	{
		return friendlyName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */

	public void setFriendlyName(String friendlyName)
	{
		this.friendlyName = friendlyName;
	}
	public int getId()
	{
		return id;
	}
	public String getMac()
	{
		return mac;
	}
	public Bluetooth(String friendlyName, int id, String mac)
	{
		super();
		this.friendlyName = friendlyName;
		this.id = id;
		this.mac = mac;
	}
	public Bluetooth()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
