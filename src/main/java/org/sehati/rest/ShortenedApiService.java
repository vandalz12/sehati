package org.sehati.rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sehati.domain.Link;
import org.sehati.util.IpChecker;
import org.sehati.util.SpringShortenedUtil;

import com.google.gson.Gson;

@Singleton
@Path("v1")
public class ShortenedApiService {

	@RolesAllowed("ADMIN")
	@POST
	@Path("/shortenedLink")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShorthenedLink(@FormParam("url") String url) {
		Link link = new Link();
		link.setOriginalLink(url);
		Gson gson = new Gson();
		String output = gson.toJson(SpringShortenedUtil.getShortenedUrlService().generateShortenedUrl(link));
		return Response.status(200).entity(output).build();
	}
	
	@RolesAllowed("ADMIN")
	@GET
	@Path("/originalLink/{shortenedLink}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOriginalLink(@Context HttpServletRequest httpServletRequest, @PathParam("shortenedLink") String shortenedLink) {
		Gson gson = new Gson();
		String output = gson.toJson(SpringShortenedUtil.getShortenedUrlService().getLinkFromShortenedUrl(shortenedLink, IpChecker.getIp(httpServletRequest.getRemoteAddr())));
		return Response.status(200).entity(output).build();
	}
	
	@PermitAll
	@POST
	@Path("/addUser")
	public Response addUser() {
		String output = "Success";
		return Response.status(200).entity(output).build();
	}
	
}
