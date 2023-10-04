package com.Demo.Example.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Demo.Example.Dto.Appointment;
import com.Demo.Example.Dto.Doctor;
import com.Demo.Example.Dto.SlotGenerator;
import com.Demo.Example.Exceptions.AppointmentNotFound;
import com.Demo.Example.Repo.AppointRepo;
import com.Demo.Example.Repo.DoctorRepo;
import com.Demo.Example.Response.ResponseStructure;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private AppointRepo appointRepo;
	
	
	public Doctor saveDoctor(Doctor doctor) {
		
		return doctorRepo.save(doctor);
	}
	
	public List<LocalDateTime[]> generateSlots(int doctId, LocalDateTime startDateTime,LocalDateTime endDateTime){
		Doctor doctor = doctorRepo.findById(doctId).get();
		if(doctor!=null) {
			List<SlotGenerator> availableSlots = doctor.getAvailableSlots();
	        List<LocalDateTime[]> generatedSlots = new ArrayList<>();

	        for (SlotGenerator availableSlot : availableSlots) {
	            LocalDateTime slotStartTime = startDateTime.withHour(availableSlot.getStartDateTime().getHour())
	                    .withMinute(availableSlot.getStartDateTime().getMinute());

	            while (slotStartTime.isBefore(startDateTime.withHour(availableSlot.getEndDateTime().getHour())
	                    .withMinute(availableSlot.getEndDateTime().getMinute()))) {
	            	 // Adjust the slot duration as per your need
	                LocalDateTime slotEndTime = slotStartTime.plusMinutes(30);
	                generatedSlots.add(new LocalDateTime[]{slotStartTime, slotEndTime});
	                slotStartTime = slotEndTime;
	            }
	        }

	        return generatedSlots;
		}
		return new  ArrayList<LocalDateTime[]>();
	    }
	public List<Appointment> availableAppoints(int id,LocalDateTime appointDateTime){
		Doctor doctor = doctorRepo.findById(id).get();
		if(doctor!=null) {
//			LocalDateTime now = LocalDateTime.now();
			return appointRepo.findByDoctorAndAvailableIsTrueAndAppointmentTimeAfter(doctor, appointDateTime);
			
		}
		return Collections.emptyList();
	}
	
	public ResponseEntity<String> bookAppointment(int id,LocalDateTime appointDateTime,String patientName) {
		Doctor doctor = doctorRepo.findById(id).get();
		if(doctor!=null) {
			List<Appointment> availableAppointments = availableAppoints(id,appointDateTime);
			if(!availableAppointments.isEmpty()) {
				//here you need choose an appointment from the available appointments and mark as booked
				Appointment bookAppointment = availableAppointments.get(0);
				bookAppointment.setAvailable(false);
				bookAppointment.setPatientName(patientName);
				appointRepo.save(bookAppointment);
				return new ResponseEntity<String>("successfully booked your appointment",HttpStatus.CREATED);
			}
			 throw new AppointmentNotFound();
		}
		return null;
	}
//	public List<Appoint> fetchAllAppointOfDoct(int doctId){
//		Doctor doctor=doctorDao.fetchDoctor(doctId);
//		if(doctor!=null) {
//			return doctor.getAppoints();
//		}
//		return null;
//	}

	public ResponseEntity<ResponseStructure<Doctor>>  fetchDoctor(int doctorId) {
		ResponseStructure<Doctor> responseStructure = new ResponseStructure<Doctor>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("doctor fetched successfully");
		responseStructure.setData(doctorRepo.findById(doctorId).get());
		return new ResponseEntity<ResponseStructure<Doctor>>(responseStructure,HttpStatus.FOUND);
		
	}
}
