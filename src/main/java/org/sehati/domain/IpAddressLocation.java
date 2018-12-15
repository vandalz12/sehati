package org.sehati.domain;

import java.io.Serializable;

import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class IpAddressLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -451410907510831L;

	private Country country;
	private Subdivision subdivision;
	private City city;
	private Postal postal;
	private Location location;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Subdivision getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Postal getPostal() {
		return postal;
	}
	public void setPostal(Postal postal) {
		this.postal = postal;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
