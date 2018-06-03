package com.agora.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
public class Debate {
	
	@Id @GeneratedValue
	private long id;
	private String topic;
	private String description;
	private String imageURL;
	private long spectators = 0;
	private boolean isLive = false;
	private long debator1Id;
	private long debator2Id;
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp; 
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public long getSpectators() {
		return spectators;
	}
	public void setSpectators(long spectators) {
		this.spectators = spectators;
	}
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	public long getDebator1Id() {
		return debator1Id;
	}
	public void setDebator1Id(long debator1Id) {
		this.debator1Id = debator1Id;
	}
	public long getDebator2Id() {
		return debator2Id;
	}
	public void setDebator2Id(long debator2Id) {
		this.debator2Id = debator2Id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Debate(long id, String topic, String description, String imageURL, long spectators, boolean isLive,
			long debator1Id, long debator2Id) {
		super();
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.imageURL = imageURL;
		this.spectators = spectators;
		this.isLive = isLive;
		this.debator1Id = debator1Id;
		this.debator2Id = debator2Id;
	}
	
	public Debate() {}
	public java.util.Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	};

	
	
	
}
