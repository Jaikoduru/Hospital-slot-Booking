package com.Demo.Example.Dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SlotGenerator {

	@Id
	private int id;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
	public SlotGenerator( LocalDateTime startDateTime, LocalDateTime endDateTime) {
		
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	
}
