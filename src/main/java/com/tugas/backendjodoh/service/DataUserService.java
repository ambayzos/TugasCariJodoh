package com.tugas.backendjodoh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tugas.backendjodoh.entity.DataUser;
import com.tugas.backendjodoh.repository.DataUserRepository;

@Service
public class DataUserService {
	
	@Autowired DataUserRepository userRepo;
	
	public DataUser getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public String saveDataUser(DataUser user) {
		userRepo.save(user);
		return "Berhasil di simpan";
	}
	
	public List<DataUser> getAllDataUser(){
		return this.userRepo.findAll();
	}
	
	public List<DataUser> getDataAllLaki(){
		return this.userRepo.findByGenderLaki();
	}
	
	public List<DataUser> getDataAllGenderPerampuan(){
		return this.userRepo.findAllGenderPerempuan();
	}
	
	
	
	
}
