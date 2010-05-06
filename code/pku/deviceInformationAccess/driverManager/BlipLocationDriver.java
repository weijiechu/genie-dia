package pku.deviceInformationAccess.driverManager;

import pku.deviceInformationAccess.locationProvider.BlipLocationProvider;
import pku.deviceInformationAccess.locationProvider.LocationProvider;

public class BlipLocationDriver extends LocationDriver
{
	static 
	{
	    try 
	    {
	    	DriverManager.registerDriver(new BlipLocationDriver());
	    } 
	    catch (Exception E)
	    {
	         throw new RuntimeException("Can't register driver!");
	    }
	}

	public BlipLocationDriver()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	LocationProvider getLocationProvider(String arg)
	{
		locationProvider = new BlipLocationProvider(arg);
		return locationProvider;
	}

	@Override
	LocationProvider getLocationProvider(String url, String userName,
			String password)
	{
		// TODO Auto-generated method stub
		locationProvider = new BlipLocationProvider(url,userName,password);
		return locationProvider;
	}
	
}
