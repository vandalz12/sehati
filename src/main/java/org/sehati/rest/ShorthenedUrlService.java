package org.sehati.rest;

import java.net.URI;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.sehati.domain.Link;
import org.sehati.util.IpChecker;
import org.sehati.util.SpringShortenedUtil;

@Singleton
@Path("/shortened")
public class ShorthenedUrlService {

	@PermitAll
	@GET
	@Path("/link/{param}")
	public Response getShorthenedUrl(@Context HttpServletRequest httpServletRequest, @PathParam("param") String param) throws Exception {
		Link link = SpringShortenedUtil.getShortenedUrlService().getLinkFromShortenedUrl(param, IpChecker.getIp(httpServletRequest.getRemoteAddr()));
		return Response.status(Status.TEMPORARY_REDIRECT).location(new URI(link.getOriginalLink())).build();
	}
	
}
