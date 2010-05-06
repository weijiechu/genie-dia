package pku.deviceInformationAccess.driverManager;

import pku.deviceInformationAccess.locationProvider.LocationProvider;

public abstract class LocationDriver
{
	static LocationProvider locationProvider;
	abstract LocationProvider getLocationProvider(String arg);
	abstract LocationProvider getLocationProvider(String url,String userName,String password);
}
