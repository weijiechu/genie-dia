package pku.deviceInformationAccess.driverManager;

import pku.deviceInformationAccess.locationProvider.LocationProvider;


public class DriverManager
{
	static LocationDriver locationDriver;
	static void registerDriver(LocationDriver locationDriver)
	{
		DriverManager.locationDriver =locationDriver; 
		System.out.println("Register Driver.");
	}
	public static LocationProvider getLocationProvider(String arg)
	{
		return locationDriver.getLocationProvider(arg);
	}
	public static LocationProvider getLocationProvider(String url,String userName,String password)
	{
		return locationDriver.getLocationProvider(url, userName, password);
	}
}
