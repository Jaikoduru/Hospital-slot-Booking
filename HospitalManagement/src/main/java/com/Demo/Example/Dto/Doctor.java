package com.Demo.Example.Dto;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int maxAppoint;
	private String specialization;
	//private List<LocalDateTime> slotsAvailable;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SlotGenerator>  availableSlots;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxAppoint() {
		return maxAppoint;
	}
	public void setMaxAppoint(int maxAppoint) {
		this.maxAppoint = maxAppoint;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public List<SlotGenerator> getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(List<SlotGenerator> availableSlots) {
		this.availableSlots = availableSlots;
	}
	
	
	
}
