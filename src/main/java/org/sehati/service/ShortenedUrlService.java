package org.sehati.service;

import org.sehati.domain.Link;

public interface ShortenedUrlService {

	public Link generateShortenedUrl(Link link);
	public Link getLinkFromShortenedUrl(String shortenedLink, String remoteIpAddress);
	
}
