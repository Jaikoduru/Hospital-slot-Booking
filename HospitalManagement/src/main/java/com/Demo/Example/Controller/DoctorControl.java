package com.Demo.Example.Controller;

import java.time.Duration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Demo.Example.Dto.Appointment;
import com.Demo.Example.Dto.Doctor;
import com.Demo.Example.Response.ResponseStructure;
import com.Demo.Example.Service.DoctorService;

@RestController
public class DoctorControl {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/save")
	public ResponseEntity<String>  saveDoctor(@RequestBody Doctor doctor,@RequestParam LocalDateTime startDateTime,@RequestParam LocalDateTime endDateTime) {
		int doctorId = doctor.getId();
		doctorService.generateSlots(doctorId, startDateTime, endDateTime);
		
		return new ResponseEntity<String>("Doctor saved successfully",HttpStatus.CREATED);
	}
	
	
	@GetMapping("/fetchDoctor")
	public ResponseEntity<ResponseStructure<Doctor>>  fetchDoctor(@RequestParam int doctorId) {
		return doctorService.fetchDoctor(doctorId);
	}
	
//	@GetMapping
//	public List<LocalDateTime> getAvailableAppoints(@RequestParam int id){
//		return appointService.getAvailableAppoints(id);
//	}
		
	@PostMapping("/saveAppoint")
	public ResponseEntity<String>  bookAppoint(@RequestParam Integer doctId, @RequestParam LocalDateTime appointDateTime,@RequestParam String patientName) {
		return doctorService.bookAppointment(doctId,appointDateTime,patientName);
	}
	
}
