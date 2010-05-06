package pku.deviceInformationAccess.location;

public class StayInZone
{
	Location enterLocation = null;
	Location leaveLoaction = null;
	private static int id = 0;
	public int theId;
	

	public StayInZone()
	{
		super();
		theId = id++;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the Id
	 */
	public int getTheId()
	{
		return theId;
	}

	/**
	 * @param theId the theId to set
	 */
	public void setTheId(int theId)
	{
		this.theId = theId;
	}

	/**
	 * @return the enterLocation
	 */
	public Location getEnterLocation()
	{
		return enterLocation;
	}
	/**
	 * @param enterLocation the enterLocation to set
	 */
	public void setEnterLocation(Location enterLocation)
	{
		this.enterLocation = enterLocation;
	}
	/**
	 * @return the leaveLoaction
	 */
	public Location getLeaveLoaction()
	{
		return leaveLoaction;
	}
	/**
	 * @param leaveLoaction the leaveLoaction to set
	 */
	public void setLeaveLoaction(Location leaveLoaction)
	{
		this.leaveLoaction = leaveLoaction;
	}
	
}
