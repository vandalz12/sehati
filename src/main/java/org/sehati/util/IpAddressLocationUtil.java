package org.sehati.util;

import java.io.InputStream;
import java.net.InetAddress;

import org.sehati.domain.IpAddressLocation;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

public class IpAddressLocationUtil {

	public IpAddressLocation getIpAddressLocation(String address) throws Exception {
		IpAddressLocation ipAddressLocation = new IpAddressLocation();
		DatabaseReader databaseReader = new DatabaseReader.Builder(getFile("geoapi2/db/GeoLite2-City.mmdb")).build();
		
		InetAddress ipAddress = InetAddress.getByName(address);
		
		CityResponse cityResponse = databaseReader.city(ipAddress);
		
		ipAddressLocation.setCountry(cityResponse.getCountry());
		ipAddressLocation.setCity(cityResponse.getCity());
		ipAddressLocation.setSubdivision(cityResponse.getMostSpecificSubdivision());
		ipAddressLocation.setPostal(cityResponse.getPostal());
		ipAddressLocation.setLocation(cityResponse.getLocation());
		
		return ipAddressLocation;
	}
	
//	private File getFile(String path) {
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource(path).getFile());
//		return file;
//	}
	
	private InputStream getFile(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream(path);
	}
	
}
