package pku.deviceInformationAccess.location;

import java.util.LinkedList;
import java.util.List;

public class Zone
{
	private int zoneID;
	private String ZoneDescription;
	private Zone superZone=null;
	private List<Coordinates> corrdinates;
	public Zone()
	{
		super();
		// TODO Auto-generated constructor stub
		corrdinates = new LinkedList<Coordinates>();
	}
	
	public Zone(int zoneID, String zoneDescription)
	{
		super();
		this.zoneID = zoneID;
		ZoneDescription = zoneDescription;
		corrdinates = new LinkedList<Coordinates>();
	}

	public Zone(int zoneID, String zoneDescription, Zone superZone)
	{
		super();
		this.zoneID = zoneID;
		ZoneDescription = zoneDescription;
		this.superZone = superZone;
		corrdinates = new LinkedList<Coordinates>();
	}
	public Zone(int zoneID, String zoneDescription, Zone superZone,List<Coordinates> corrdinates)
	{
		super();
		this.zoneID = zoneID;
		ZoneDescription = zoneDescription;
		this.superZone = superZone;
		this.corrdinates = corrdinates;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + zoneID;
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
		if (!(obj instanceof Zone))
			return false;
		Zone other = (Zone) obj;
		if (zoneID != other.zoneID)
			return false;
		return true;
	}

	/**
	 * @return the zoneID
	 */
	public int getZoneID()
	{
		return zoneID;
	}
	/**
	 * @param zoneID the zoneID to set
	 */
	public void setZoneID(int zoneID)
	{
		this.zoneID = zoneID;
	}
	/**
	 * @return the zoneDescription
	 */
	public String getZoneDescription()
	{
		return ZoneDescription;
	}
	/**
	 * @param zoneDescription the zoneDescription to set
	 */
	public void setZoneDescription(String zoneDescription)
	{
		ZoneDescription = zoneDescription;
	}
	/**
	 * @return the superZone
	 */
	public Zone getSuperZone()
	{
		return superZone;
	}
	/**
	 * @param superZone the superZone to set
	 */
	public void setSuperZone(Zone superZone)
	{
		this.superZone = superZone;
	}
	/**
	 * @return the corrdinates
	 */
	public List<Coordinates> getCorrdinates()
	{
		return corrdinates;
	}
	/**
	 * @param corrdinates the corrdinates to set
	 */
	public void setCorrdinates(List<Coordinates> corrdinates)
	{
		this.corrdinates = corrdinates;
	}
	
	
}
