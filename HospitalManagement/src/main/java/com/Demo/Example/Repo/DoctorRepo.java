package com.Demo.Example.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.Example.Dto.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>
{

}
