package org.sehati.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class Sequence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8153829625263830206L;
	
	@Id
	private String id;
	private long sequence;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	
}
