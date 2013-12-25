package it.meet.entity;

import java.sql.Timestamp;

public class BlackContact extends User{

	private Timestamp timestamp;

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
