package tn.esprit.spring.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import request.LoginRequest;
import request.SignupRequest;
import response.JwtResponse;
import response.MessageResponse;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CreditServiceImpl;
import tn.esprit.spring.service.UserDetailsImpl;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.message.ResponseMessage;
import tn.esprit.spring.repository.RoleRepository;
import jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
 
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();  
    User u=userRepository.findById(userDetails.getId()).orElse(null);
    log.info("ss"+u.getId());
    ResponseMessage customResponse = new ResponseMessage("Ce compte est désactivé");
    if(u.getActive()==false) {
    	return ResponseEntity.ok(customResponse);
    }
    else {
    	List<String> roles = userDetails.getAuthorities().stream()
    	        .map(item -> item.getAuthority())
    	        .collect(Collectors.toList());

    	    return ResponseEntity.ok(new JwtResponse(jwt, 
    	                         userDetails.getId(), 
    	                         userDetails.getUsername(), 
    	                         userDetails.getEmail(), 
    	                         roles));
    }
    
  }

  @PostMapping("/signup")
  public User registerUser( @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    String strRoles = signUpRequest.getRole();


    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      user.setRoles(userRole);
    } else {

        switch (strRoles) {
        case "ROLE_ENTREPRENEUR":
          Role entrepreneurRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          user.setRoles(entrepreneurRole);

          break;
        case "ROLE_ADMIN":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          user.setRoles(adminRole);

          break;
        default:
          Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          user.setRoles(agentRole);;
        }

    }


    user.setActive(true);
    user.setNom(signUpRequest.getNom());
    user.setPrenom(signUpRequest.getPrenom());
    user.setTel(signUpRequest.getTel());
    user.setAdresse(signUpRequest.getAdresse());
    

    return userRepository.save(user);
  }
  

}
