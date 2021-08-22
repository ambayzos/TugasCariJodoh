package com.tugas.backendjodoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tugas.backendjodoh.entity.DataUser;

import net.bytebuddy.build.HashCodeAndEqualsPlugin.ValueHandling.Sort;


public interface DataUserRepository extends JpaRepository<DataUser, Long> {
	DataUser findByUsername(String username);
	
	@Query(value = "Select * from data_user where gender='LAKI-LAKI'",nativeQuery = true)
	List<DataUser> findByGenderLaki();
	
	@Query(value = "Select * from data_user where gender='PEREMPUAN'",nativeQuery = true)
	List<DataUser> findAllGenderPerempuan();
	
	
	
	
	 
	
}
