package org.sehati.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IpChecker {

	public static String getIp(String ipAddress) {
		String ip = "";
		
		if(!isPrivateIpV4Address(ipAddress)) {
			ip = ipAddress;
		} else {
			BufferedReader in = null;
			try {
				URL whatismyip = new URL("http://checkip.amazonaws.com");
				in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
				ip = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
        return ip;
    }
	
	private static boolean isPrivateIpV4Address(String ipAddress) {
		String[] ipAddressSegment = ipAddress.split("\\.");
		short[] ipSegment = new short[] {
											Short.parseShort(ipAddressSegment[0]),
											Short.parseShort(ipAddressSegment[1]),
											Short.parseShort(ipAddressSegment[2]),
											Short.parseShort(ipAddressSegment[3])
										};
		
		if(
			(ipSegment[0] == 10) || // Class A
			(ipSegment[0] == 172 && ipSegment[1] >= 16 && ipSegment[1] <= 31) || // Class B
			(ipSegment[0] == 192 && ipSegment[1] == 168) || // Class C
			(ipSegment[0] == 127 && ipSegment[1] == 0 && ipSegment[2] == 0 && ipSegment[3] == 1) // Loopback
		  ) {
			return true;
		}
		
		return false;
	}
	
}
