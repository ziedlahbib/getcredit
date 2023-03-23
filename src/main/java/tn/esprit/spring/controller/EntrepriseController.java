package tn.esprit.spring.controller;

import java.util.List;

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

import Interface.IEntrepriseservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.User;




@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {
	@Autowired
	IEntrepriseservice entrepriseService;
	
	@PostMapping("/add-entreprise")
	@ResponseBody
	public Entreprise addEntreprise(@RequestBody Entreprise u) {
		return entrepriseService.AjoutEntreprise(u);

	}
	
	@PutMapping("/update-entreprise/{id-ent}")
	@ResponseBody
	public Entreprise upadateEntreprise(@RequestBody Entreprise u,@PathVariable("id-ent") Long idENt) {
		return entrepriseService.UpdateEntreprise(u, idENt);

	}
	
	@DeleteMapping("/delete-entreprise/{id-ent}")
	@ResponseBody
	public void deleteEntreprise(@PathVariable("id-ent") Long idENt) {
		entrepriseService.SupprimerEntreprise(idENt);

	}

	@GetMapping("/get-entreprsie/{id-ent}")
	@ResponseBody
	public Entreprise getEntreprisebyid(@PathVariable("id-ent") Long idENt) {
		return entrepriseService.AffichDetailEntreprise(idENt);

	}
	
	@GetMapping("/get-entreprises")
	@ResponseBody
	public List<Entreprise> getEntreprises() {
		return entrepriseService.afiichListEntreprise();

	}
	@PutMapping("/affecter-utilisateur-entreprise/{id-user}/{id-ent}")
	@ResponseBody
	public Entreprise affecteruserauentreprise(@PathVariable("id-user") Long iduser,@PathVariable("id-ent") Long ident) {
		return entrepriseService.affecteruserauentreprise(iduser, ident);

	}
}
