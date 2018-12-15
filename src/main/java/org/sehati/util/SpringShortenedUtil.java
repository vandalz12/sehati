package org.sehati.util;

import org.sehati.service.ShortenedUrlService;
import org.sehati.service.UserService;

public class SpringShortenedUtil {

	public static ShortenedUrlService getShortenedUrlService() {
		return (ShortenedUrlService) SpringContextUtil.getApplicationContext().getBean("shortenedUrlServiceImpl");
	}
	
	public static UserService getUserServiceImpl() {
		return (UserService) SpringContextUtil.getApplicationContext().getBean("userServiceImpl");
	}
	
}
