package org.sehati.util;

public class ShortenedUtil {

	private static final String dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int dictionaryLength = dictionary.length();
	
	public static String encode(long key) {
		String hash = "";
		long counter = key;
		int mod;
		char code;
		while(counter > 0) {
			mod = (int) (counter % dictionaryLength);
			code = dictionary.charAt(mod);
			hash = String.valueOf(code) + hash;
			counter = (long) (counter / dictionaryLength);
		}
		
		return hash;
	}
	
	public static long decode(String hash) {
		long key = 0;
		int hashLength = hash.length();
		for(int i = 0; i < hashLength; i++) {
			key += (((long) (dictionary.indexOf(hash.charAt(i)))) * ((long) (Math.pow(dictionaryLength, ((hashLength - 1) - i)))));
		}
		return key;
	}
	
}
