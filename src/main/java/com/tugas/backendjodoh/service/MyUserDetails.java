package com.tugas.backendjodoh.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tugas.backendjodoh.entity.DataUser;
import com.tugas.backendjodoh.repository.DataUserRepository;

@Service
public class MyUserDetails implements UserDetailsService {
	
	@Autowired DataUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<SimpleGrantedAuthority> genders = null;
		DataUser user = userRepo.findByUsername(username);
		
		if (user == null) {
			
				throw new UsernameNotFoundException("User Tidak Ditemukan Dengan Username :"+username);
		}
		
		genders = Arrays.asList(new SimpleGrantedAuthority(user.getGender()));
		return new User(user.getUsername(), user.getPassword(), genders);
	}
	
}
