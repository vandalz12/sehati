package org.sehati.constant;

public enum BaseShortenedUrl {

	BASE_SHORTENED_URL("http://localhost:9080/shortenedurl/");
	
	private String description;
	
	private BaseShortenedUrl(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
