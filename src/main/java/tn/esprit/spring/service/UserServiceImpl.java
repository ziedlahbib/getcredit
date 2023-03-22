package tn.esprit.spring.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Interface.IUserservice;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.FileDB;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.FileDBRepository;
import tn.esprit.spring.repository.UserRepository;
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
		u.setUserName(user.getUserName());
		u.setRole(user.getRole());	
		u.setResetToken(user.getResetToken());
		return userRepo.save(u);
		
	}


	@Override
	public User updatepassword(User user, Long idUser) {
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
		u.setUserName(user.getUserName());
		u.setRole(user.getRole());	
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
	public User findbyusername(String username) {
		return userRepo.findByUserName(username);
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

	

}
