package tn.esprit.spring.service;


import java.util.ArrayList;
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
import request.SignupRequest;
import tn.esprit.spring.entity.Banned;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.FileDB;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.BannedRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.FileDBRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.MagasinRepository;

import jwt.AuthEntryPointJwt;
@Service
@Slf4j
public class UserServiceImpl implements IUserservice {
	@Autowired
	UserRepository userRepo;
	@Autowired
	FileDBRepository fileDBRepo;
	@Autowired
	RoleRepository roleRepository;
	 @Autowired
	 EntrepriseRepository entRepo;
	 @Autowired
	 MagasinRepository magRepo;
	 @Autowired
	 BannedRepository banRepo;

	@Override
	public User updateUser(SignupRequest signUpRequest, Long idUser) {
		User u = userRepo.findById(idUser).orElse(null);
		u.setAdresse(signUpRequest.getAdresse());
		u.setEmail(signUpRequest.getEmail());
		u.setNom(signUpRequest.getNom());
		u.setPrenom(signUpRequest.getPrenom());
		u.setTel(signUpRequest.getTel());
		u.setUsername(signUpRequest.getUsername());
//		u.setActive(signUpRequest.getActive());
//		String strRoles = signUpRequest.getRole();
//
//
//	    if (strRoles == null) {
//	      Role userRole = roleRepository.findByName(ERole.ROLE_AGENT)
//	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	      u.setRoles(userRole);
//	    } else {
//
//	        switch (strRoles) {
//	        case "entrepreneur":
//	          Role entrepreneurRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          u.setRoles(entrepreneurRole);
//
//	          break;
//	        case "admin":
//	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          u.setRoles(adminRole);
//
//	          break;
//	        default:
//	          Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          u.setRoles(agentRole);;
//	        }

//	    }
    	return userRepo.save(u);
	}


	@Override
	public User updateclient(User user, Long idUser) {
		User u =userRepo.findById(idUser).orElse(null);
		u.setAdresse(user.getAdresse());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setEmail(user.getEmail());
		u.setTel(user.getTel());
		return userRepo.save(u);
	}
	@Override
	public User resetpassword(User user) {	
		return userRepo.save(user);
	}
	@Override
	public void deleteUser(Long idUser) {
		User u =userRepo.findById(idUser).orElse(null);
		u.setRoles(null);
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
	        	userRepo.save(u);
	        	return "Mot de passe mis à jour avec succès";

	        }
	        
	        else {
	            return "Ancien mot de passe invalide";
	        }
	   }
	

	 public boolean verifyPassword(User user, String password) {
	        return passwordEncoder().matches(password, user.getPassword());
	    }
	 private PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 /////////////////////////////////////////////////
	 


	@Override
	public User affecteruseraumagasin(Long iduser, Long idmag) {
		User u = userRepo.findById(iduser).orElse(null);
		Magasin m = magRepo.findById(idmag).orElse(null);
		u.setMagasin(m);
		return userRepo.save(u);
	}


	@Override
	public User affecteragentauentrepreneur(Long idagent, Long ident) {
		User a = userRepo.findById(idagent).orElse(null);
		User ent = userRepo.findById(ident).orElse(null);
		a.setEntrepreneur(ent);
		return userRepo.save(a);
	}


	@Override
	public List<User> getusersbyEntrepreneur(Long idEnt) {
		User ent = userRepo.findById(idEnt).orElse(null);
		return ent.getAgents();
	}


	@Override
	public List<User> getusersbyagent(Long idagent) {
		User a = userRepo.findById(idagent).orElse(null);
		User ent = a.getEntrepreneur();
		List<Magasin> lsm =new ArrayList<Magasin>();
		List <User> lue =new ArrayList<User>();
		//List <User> l = ent.getAgents();

		for(Entreprise e:ent.getEntreprise()) {
			for(Magasin m :e.getMagasins()) {
				if(a.getMagasin().getEntreprise().getEntrpriseId()==m.getEntreprise().getEntrpriseId()) {
					lsm.add(m);
				}
			}
			
		}
		
		for(Magasin m :lsm) {
			log.info("ssss"+m.getMagasinId());
			List <User> lum = this.getusersbymagasin(m.getMagasinId());

				lue.addAll(lum);
			
			
		}
		return lue;
	}


	@Override
	public List<User> getusersbymagasin(Long idmagasin) {
		
		return userRepo.userparmagasin(idmagasin) ;
	}


	@Override
	public User ajoutclient(User user) {
		Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
		user.setRoles(userRole);
		return  userRepo.save(user);
		
		
	}


	@Override
	public void activer(Long iduser) {
		User u = userRepo.findById(iduser).orElse(null);
		List<Banned> lsb=banRepo.findAll();
		List<User> lsu =new ArrayList<User>();
		for(Banned b :lsb) {
			lsu.add(b.getUser());
		}
		u.setActive(true);
		for(Banned b :u.getBan()) {
//			b.setUser(null);
			banRepo.deleteById(b.getId());
		}
		if(u.getRoles().getName()==ERole.ROLE_ENTREPRENEUR) {
			for(User us :u.getAgents()) {
					if(!lsu.contains(us)) {
						us.setActive(true);
						userRepo.save(us);							
						}
					
					}
				}
				}
		
		
	


	@Override
	public void desactiver(Long iduser) {
		User u = userRepo.findById(iduser).orElse(null);
		Banned b=new Banned();
		
		u.setActive(false);
		b.setUser(u);
		banRepo.save(b);
		if(u.getRoles().getName()==ERole.ROLE_ENTREPRENEUR) {
			for(User us :u.getAgents()) {
				us.setActive(false);
				userRepo.save(us);
			}
		}
		
	}


}
