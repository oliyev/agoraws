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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
@Table(name = "debates")
public class Debate {
	
	@Id @GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
	private User debater;
	
	private long opponent;
	private int debaterPoints;
	private int opponentPoints;
	
	//Constructor
	public Debate() {}
	
	public Debate(long id, User debater, long opponent, int debaterPoints, int opponentPoints) {
		super();
		this.id = id;
		this.debater = debater;
		this.opponent = opponent;
		this.debaterPoints = debaterPoints;
		this.opponentPoints = opponentPoints;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getdebater() {
		return debater;
	}

	public void setdebater(User debater) {
		this.debater = debater;
	}

	public long getOpponent() {
		return opponent;
	}

	public void setOpponent(long opponent) {
		this.opponent = opponent;
	}

	public int getdebaterPoints() {
		return debaterPoints;
	}

	public void setdebaterPoints(int debaterPoints) {
		this.debaterPoints = debaterPoints;
	}

	public int getopponentPoints() {
		return opponentPoints;
	}

	public void setopponentPoints(int opponentPoints) {
		this.opponentPoints = opponentPoints;
	}
}
