package it.meet.entity;

import java.util.Date;


public class BlackContact extends User{

	private Date blockedDate;

	public Date getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(Date blockedDate) {
		this.blockedDate = blockedDate;
	}
}
