package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Interface.IUserservice;
import request.ChangePasswordRequest;
import request.SignupRequest;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.User;




@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserservice userServ;
	
	@PostMapping("/add-client")
	@ResponseBody
	public User addcredit(@RequestBody User u) {
		return userServ.ajoutclient(u);

	}
	@PutMapping("/update-client/{id-client}")
	@ResponseBody
	public User upadateProduit(@RequestBody User u,@PathVariable("id-client") Long idclient) {
		return userServ.updateclient(u, idclient);

	}
	@PutMapping("/update-password/{id-user}")
	@ResponseBody
	public String upadetepassword(@RequestBody ChangePasswordRequest request,@PathVariable("id-user") Long iduser) {
		return userServ.updatepassword(request, iduser);

	}
	@PutMapping("/activer-user/{id-user}")
	@ResponseBody
	public void activeruser(@PathVariable("id-user") Long iduser) {
		 userServ.activer( iduser);

	}
	@PutMapping("/desactiver-user/{id-user}")
	@ResponseBody
	public void desactiveruser(@PathVariable("id-user") Long iduser) {
		 userServ.desactiver( iduser);

	}

	@PutMapping("/update-utilisateur/{id-user}")
	@ResponseBody
	public User upadeteutilisateur(@RequestBody SignupRequest signUpRequest,@PathVariable("id-user") Long iduser) {
		return userServ.updateUser(signUpRequest, iduser);

	}
	@DeleteMapping("/delete-user/{id-user}")
	@ResponseBody
	public void deleteuser(@PathVariable("id-user") Long iduser) {
		userServ.deleteUser(iduser);

	}
	@GetMapping("/get-user/{id-user}")
	@ResponseBody
	public User getuserbyid(@PathVariable("id-user") Long iduser) {
		return userServ.affichDetailUser(iduser);

	}
	@GetMapping("/get-users")
	@ResponseBody
	public List<User> getusers() {
		return userServ.affichUser();

	}
	@GetMapping("/get-userbyusername/{username}")
	@ResponseBody
	public Optional<User> getbyusername(@PathVariable("username") String username) {
		return userServ.findbyusername(username);

	}
	@GetMapping("/get-userbyemail/{email}")
	@ResponseBody
	public Optional<User> getbyuseremail(@PathVariable("email") String email) {
		return userServ.findUserByEmail(email);

	}
	@PutMapping("/affecter-file-utilisateur/{id-user}/{id-file}")
	@ResponseBody
	public User affecterfileutilisateur(@PathVariable("id-user") Long iduser,@PathVariable("id-file") Long idfile) {
		return userServ.affcterfileauuser(iduser, idfile);

	}

	@PutMapping("/affecter-utilisateur-magasin/{id-user}/{id-mag}")
	@ResponseBody
	public User affecteruseraumagasin(@PathVariable("id-user") Long iduser,@PathVariable("id-mag") Long idmag) {
		return userServ.affecteruseraumagasin(iduser, idmag);

	}
	@PutMapping("/affecter-utilisateur-entrepreneur/{id-user}/{id-ent}")
	@ResponseBody
	public User affecteruserauentrpreneur(@PathVariable("id-user") Long iduser,@PathVariable("id-ent") Long ident) {
		return userServ.affecteragentauentrepreneur(iduser, ident);

	}
	@GetMapping("/get-userbyentrepreneur/{id-ent}")
	@ResponseBody
	public List<User> getbyuserbyentrepreneur(@PathVariable("id-ent") Long ident) {
		return userServ.getusersbyEntrepreneur(ident);

	}
	
	@GetMapping("/get-userbyagent/{id-agent}")
	@ResponseBody
	public List<User> getbyuserbyagent(@PathVariable("id-agent") Long idagent) {
		return userServ.getusersbyagent(idagent);

	}
	@GetMapping("/get-userbymagasin/{id-mag}")
	@ResponseBody
	public List<User> getbyuserbymagasin(@PathVariable("id-mag") Long idmagasin) {
		return userServ.getusersbymagasin(idmagasin);

	}
	
}
