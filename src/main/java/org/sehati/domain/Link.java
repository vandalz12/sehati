package org.sehati.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "links")
public class Link implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -157115851228444663L;
	
	@Field("id")
	private String id;
	private String originalLink;
	private String shortenedLink;
	private long sequence;
	private List<LinkDetail> linkDetails = new ArrayList<LinkDetail>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalLink() {
		return originalLink;
	}
	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}
	public String getShortenedLink() {
		return shortenedLink;
	}
	public void setShortenedLink(String shortenedLink) {
		this.shortenedLink = shortenedLink;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public List<LinkDetail> getLinkDetails() {
		return linkDetails;
	}
	public void setLinkDetails(List<LinkDetail> linkDetails) {
		this.linkDetails = linkDetails;
	}
	
	
}
