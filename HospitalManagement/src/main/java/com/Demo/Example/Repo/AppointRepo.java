package com.Demo.Example.Repo;

import java.time.LocalDateTime;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Demo.Example.Dto.Appointment;
import com.Demo.Example.Dto.Doctor;
@Repository
public interface AppointRepo extends JpaRepository<Appointment, Integer>
{
	List<Appointment> findByDoctorAndAvailableIsTrueAndAppointmentTimeAfter(Doctor doctor, LocalDateTime bookSlotOfTime);

}
