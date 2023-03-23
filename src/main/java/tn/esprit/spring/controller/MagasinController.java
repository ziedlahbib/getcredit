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

import Interface.IMagasinservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Magasin;

@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/magasin")
public class MagasinController {
	@Autowired
	IMagasinservice magasinServ;
	
	@PostMapping("/add-magasin")
	@ResponseBody
	public Magasin addmagasin(@RequestBody Magasin u) {
		return magasinServ.AjoutMagasin(u);

	}
	
	@PutMapping("/update-magasin/{id-magasin}")
	@ResponseBody
	public Magasin upadatemagasin(@RequestBody Magasin u,@PathVariable("id-magasin") Long idmagasin) {
		return magasinServ.UpdateMagasin(u, idmagasin);

	}
	
	@DeleteMapping("/delete-magasin/{id-magasin}")
	@ResponseBody
	public void deletemagasin(@PathVariable("id-magasin") Long idmagasin) {
		magasinServ.SupprimerMagasin(idmagasin);

	}

	@GetMapping("/get-magasin/{id-magasin}")
	@ResponseBody
	public Magasin getmagasinbyid(@PathVariable("id-magasin") Long idmagasin) {
		return magasinServ.AffichDetailMagasin(idmagasin);

	}
	
	@GetMapping("/get-magasins")
	@ResponseBody
	public List<Magasin> getmagasins() {
		return magasinServ.afiichListMagasin();

	}
	@PutMapping("/affecter-magasin-entreprise/{id-magasin}/{id-entreprise}")
	@ResponseBody
	public Magasin upadatemagasin(@PathVariable("id-magasin") Long idmagasin,@PathVariable("id-entreprise") Long idEnt) {
		return magasinServ.affectermagasinaentreprise(idmagasin, idEnt);

	}
}
