package org.sehati.service.impl;

import org.sehati.constant.BaseShortenedUrl;
import org.sehati.domain.IpAddressLocation;
import org.sehati.domain.Link;
import org.sehati.domain.LinkDetail;
import org.sehati.domain.Sequence;
import org.sehati.service.ShortenedUrlService;
import org.sehati.util.IpAddressLocationUtil;
import org.sehati.util.ShortenedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service("shortenedUrlServiceImpl")
public class ShortenedUrlServiceImpl implements ShortenedUrlService {

	@Autowired @Qualifier("mongoTemplate") private MongoOperations mongoOperations;
	
	public Link generateShortenedUrl(Link link) {
		
		Sequence sequence = getSequence("shortened");
		link.setSequence(sequence.getSequence());
		link.setShortenedLink(BaseShortenedUrl.BASE_SHORTENED_URL.getDescription() + ShortenedUtil.encode(sequence.getSequence()));
		
		mongoOperations.save(link);
		
		
		return link;
	}

	private Sequence getSequence(String key) {
		
		Query query = new Query(Criteria.where("_id").is(key));
		
		Update update = new Update();
		update.inc("sequence", 1);
		
		FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
		findAndModifyOptions.returnNew(true);
		
		Sequence sequence = null;
		
		try {
			sequence = mongoOperations.findAndModify(query, update, findAndModifyOptions, Sequence.class);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
		
		return sequence;
	}

	public Link getLinkFromShortenedUrl(String shortenedLink, String remoteIpAddress) {
		long key = ShortenedUtil.decode(shortenedLink);
		
		Query query = new Query(Criteria.where("sequence").is(key));
		
		Link link = mongoOperations.findOne(query, Link.class);
		
		IpAddressLocationUtil ipAddressLocationUtil = new IpAddressLocationUtil();
		try {
			IpAddressLocation ipAddressLocation = ipAddressLocationUtil.getIpAddressLocation(remoteIpAddress);
			LinkDetail linkDetail = new LinkDetail();
			linkDetail.setIpAddress(remoteIpAddress);
			linkDetail.setLinkId(link.getId());
			linkDetail.setCountryName(ipAddressLocation.getCountry().getName());
			linkDetail.setSubdivisionName(ipAddressLocation.getSubdivision().getName());
			linkDetail.setCityName(ipAddressLocation.getCity().getName());
			linkDetail.setPostalCode(ipAddressLocation.getPostal().getCode());
			linkDetail.setLatitude(ipAddressLocation.getLocation().getLatitude());
			linkDetail.setLongitude(ipAddressLocation.getLocation().getLongitude());
			link.getLinkDetails().add(linkDetail);
			mongoOperations.save(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		link.getLinkDetails().clear();
		return link;
	}

}
