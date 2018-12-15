package org.sehati.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import org.bson.Document;
import org.sehati.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jersey.repackaged.com.google.common.net.InetAddresses;

public class Test {

	public static void main(String[] args) {
//		long key = 1L;
//		String hash = ShortenedUtil.encode(key);
//		System.out.println();
//		long code = ShortenedUtil.decode(hash);
//		
//		System.out.println();
//		System.out.println(key);
//		System.out.println(hash);
//		System.out.println(code);
		
//		Test test = new Test();
//		test.play();
//		System.out.println(File.separator);
		
//		MongoClient mongoClient = new MongoClient("localhost", 17021);
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user = new User();
		user.setUsername("User1");
		user.setPassword("password");
		user.setRole("ADMIN");
		
		MongoOperations mongoOperations = (MongoOperations) applicationContext.getBean("mongoTemplate");
		mongoOperations.save(user);
		
//		MongoDatabase mongoDatabase = mongoClient.getDatabase("sehati");
//		
//		MongoCollection<Document> collection = mongoDatabase.getCollection("users");
//		Document document = new Document("username", "user")
//				.append("password", "password")
//				.append("role", "ADMIN");
//		collection.insertOne(document);
//		mongoClient.close();
		System.out.println("SUKSES");
		
//		System.out.println((InetAddresses.coerceToInteger(InetAddresses.forString("103.56.235.158")) >> 16) & 0xFF);
		
	}
	
	public void play() {
		try {
			DatabaseReader databaseReader = new DatabaseReader.Builder(getFile("geoapi2/db/GeoLite2-City.mmdb")).build();
			
			InetAddress ipAddress = InetAddress.getByName("103.56.235.158");
			
			CityResponse cityResponse = databaseReader.city(ipAddress);
			
			// Country Info
	       Country country = cityResponse.getCountry();
	       System.out.println("Country IsoCode: "+ country.getIsoCode()); // 'US'
	       System.out.println("Country Name: "+ country.getName()); // 'United States'
//	       System.out.println(country.getNames().get("zh-CN"));
	 
	       Subdivision subdivision = cityResponse.getMostSpecificSubdivision();
	       System.out.println("Subdivision Name: " +subdivision.getName()); // 'Minnesota'
	       System.out.println("Subdivision IsoCode: "+subdivision.getIsoCode()); // 'MN'
	 
	       // City Info.
	       City city = cityResponse.getCity();
	       System.out.println("City Name: "+ city.getName()); // 'Minneapolis'
	 
	       // Postal info
	       Postal postal = cityResponse.getPostal();
	       System.out.println(postal.getCode()); // '55455'
	 
	       // Geo Location info.
	       Location location = cityResponse.getLocation();
	        
	       // Latitude
	       System.out.println("Latitude: "+ location.getLatitude()); // 44.9733
	        
	       // Longitude
	       System.out.println("Longitude: "+ location.getLongitude()); // -93.2323
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	private File getFile(String path) {
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource(path).getFile());
//		return file;
//	}
	
	private InputStream getFile(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource(path).getFile());
		return classLoader.getResourceAsStream(path);
	}
	
}
