package tn.esprit.spring.service;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Interface.IUserservice;
import lombok.extern.slf4j.Slf4j;
import request.ChangePasswordRequest;
import tn.esprit.spring.entity.FileDB;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.FileDBRepository;
import tn.esprit.spring.repository.UserRepository;

import jwt.AuthEntryPointJwt;
@Service
@Slf4j
public class UserServiceImpl implements IUserservice {
	@Autowired
	UserRepository userRepo;
	@Autowired
	FileDBRepository fileDBRepo;

	@Override
	public User updateUser(User user, Long idUser) {
		User u = userRepo.findById(idUser).orElse(null);
		u.setAdresse(user.getAdresse());
		u.setEmail(user.getEmail());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setTel(user.getTel());
		u.setUsername(user.getUsername());
		u.setActive(user.getActive());
		u.setResetToken(u.getResetToken());
		return userRepo.save(u);
		
	}


	@Override
	public User resetpassword(User user, Long idUser) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		User u = userRepo.findById(idUser).orElse(null);
		log.info("ccc"+u.getNom());
		// Set new password    
		
		u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// Set the reset token to null so it cannot be used again
		u.setResetToken(null);
		u.setAdresse(user.getAdresse());
		u.setEmail(user.getEmail());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setTel(user.getTel());
		u.setUsername(user.getUsername());	
		return userRepo.save(u);
	}
	@Override
	public void deleteUser(Long idUser) {

		userRepo.deleteById(idUser);;
		
	}

	@Override
	public User affichDetailUser(Long idUser) {
		// TODO Auto-generated method stub
		return userRepo.findById(idUser).orElse(null);
	}

	@Override
	public List<User> affichUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
	@Override
	public Optional<User> findbyusername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User affcterfileauuser(Long iduser,Long idfile) {
		// TODO Auto-generated method stub
		User u = userRepo.findById(iduser).orElse(null);
		FileDB f = fileDBRepo.findById(idfile).orElse(null);
		f.setUser(u);
		fileDBRepo.save(f);
		return u;
	}
	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public Optional<User> findUserByResetToken(String resetToken) {
		return userRepo.findByResetToken(resetToken);
	}

/////////////////////
	 public String updatepassword( ChangePasswordRequest changePasswordRequest, Long iduser) {
		User u = userRepo.findById(iduser).orElse(null);
	        if (verifyPassword(u, changePasswordRequest.getOldpassword())) {
	        	u.setPassword(passwordEncoder().encode(changePasswordRequest.getNewpassword()));
	    		u.setAdresse(u.getAdresse());
	    		u.setEmail(u.getEmail());
	    		u.setNom(u.getNom());
	    		u.setPrenom(u.getPrenom());
	    		u.setTel(u.getTel());
	    		u.setUsername(u.getUsername());
	    		u.setActive(u.getActive());
	    		u.setResetToken(u.getResetToken());
	        	userRepo.save(u);
	        	return "Password updated successfully";

	        }
	        
	        else {
	            return "Invalid old password";
	        }
	   }
	

	 public boolean verifyPassword(User user, String password) {
	        return passwordEncoder().matches(password, user.getPassword());
	    }
	 private PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 /////////////////////////////////////////////////
}
