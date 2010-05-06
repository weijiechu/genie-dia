package pku.deviceInformationAccess.location;

import java.util.Date;


public class Location
{
	private Zone zone;
	private Coordinates coordinates;
	private Date time;
	public Location(Zone zone, Coordinates coordinates,Date time)
	{
		super();
		this.zone = zone;
		this.coordinates = coordinates;
		this.time = time;
	}
	public Location()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the time
	 */
	public Date getTime()
	{
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time)
	{
		this.time = time;
	}
	/**
	 * @return the zone
	 */
	public Zone getZone()
	{
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(Zone zone)
	{
		this.zone = zone;
	}
	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates()
	{
		return coordinates;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}

}
