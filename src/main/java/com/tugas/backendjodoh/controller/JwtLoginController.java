package com.tugas.backendjodoh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.tugas.backendjodoh.config.JwtTokenUtil;
import com.tugas.backendjodoh.entity.DataUser;
import com.tugas.backendjodoh.service.DataUserService;
import com.tugas.backendjodoh.service.MyUserDetails;
import com.tugas.backendjodoh.utility.FileUtility;


@RestController
public class JwtLoginController {

	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@Autowired
	private MyUserDetails uService;
	
	@Autowired 
	private DataUserService duService;
	
	@GetMapping("/getalluser")
	public ResponseEntity<?> getAllUser(){
		return ResponseEntity.ok(duService.getAllDataUser());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody DataUser dataUser)throws Exception{
		authenticate(dataUser.getUsername(), dataUser.getPassword());
		
		final UserDetails userDetails = uService.loadUserByUsername(dataUser.getUsername());
		final String token = tokenUtil.generateToken(userDetails, duService.getUserByUsername(dataUser.getUsername()).getGender());
	
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON)throws Exception{
		
		
		String filename = StringUtils.cleanPath(images.getOriginalFilename());
		String uploadDir="user-photos/";
		FileUtility.saveFile(uploadDir, filename, images);
		DataUser dataUser = new Gson().fromJson(dataJSON, DataUser.class);
		
		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		dataUser.setPassword(passwordEncode.encode(dataUser.getPassword()));
		
		dataUser.setDataFoto(filename);
		
		return ResponseEntity.ok(duService.saveDataUser(dataUser));
	}
	
	@GetMapping("/getlaki")
	public ResponseEntity<?> getAllPerempuan(){
		return ResponseEntity.ok(duService.getDataAllLaki());
	}
	
	@GetMapping("/getperempuan")
	public ResponseEntity<?> getAllGenderPerempuan(){
		//String dataPerempuan = "PEREMPUAN"+getAllGender(gender);
		
		return ResponseEntity.ok(duService.getDataAllGenderPerampuan());
	}
	
	
	
	
	private void authenticate(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER_DISABLED",e);
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
}
