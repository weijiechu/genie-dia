package pku.deviceInformationAccess.terminal;

public class WiFiEquipment extends Terminal
{
	
	public WiFiEquipment(String friendlyName, int id, String mac)
	{
		super();
		this.friendlyName = friendlyName;
		this.id = id;
		this.mac = mac;
	}
	public WiFiEquipment()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the friendlyName
	 */
	public String getFriendlyName()
	{
		return friendlyName;
	}
	/**
	 * @param friendlyName the friendlyName to set
	 */
	public void setFriendlyName(String friendlyName)
	{
		this.friendlyName = friendlyName;
	}
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * @return the mac
	 */
	public String getMac()
	{
		return mac;
	}
	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac)
	{
		this.mac = mac;
	}
	
	
}
