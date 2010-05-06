package pku.deviceInformationAccess.driverManager;

import pku.deviceInformationAccess.locationProvider.WiFiLocaitonProvider;
import pku.deviceInformationAccess.locationProvider.LocationProvider;

public class WiFiLocationDriver extends LocationDriver
{

	static 
	{
	    try 
	    {
	    	DriverManager.registerDriver(new WiFiLocationDriver());
	    } 
	    catch (Exception E)
	    {
	         throw new RuntimeException("Can't register driver!");
	    }
	}
	@Override
	LocationProvider getLocationProvider(String arg)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	LocationProvider getLocationProvider(String url, String userName,
			String password)
	{
		locationProvider = new WiFiLocaitonProvider(url,userName,password);
		return locationProvider;
	}

}
