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

import Interface.IProduitservice;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.entity.Produit;

@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/produit")
public class ProduitController {
	@Autowired
	IProduitservice produitServ;
	
	@PostMapping("/add-produit")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit u) {
		return produitServ.AjoutProduit(u);

	}
	
	@PutMapping("/update-magasin/{id-produit}")
	@ResponseBody
	public Produit upadateProduit(@RequestBody Produit u,@PathVariable("id-produit") Long idproduit) {
		return produitServ.UpdateProduit(u, idproduit);

	}
	
	@DeleteMapping("/delete-produit/{id-produit}")
	@ResponseBody
	public void deleteProduit(@PathVariable("id-produit") Long idproduit) {
		produitServ.SupprimerProduit(idproduit);

	}

	@GetMapping("/get-produit/{id-magasin}")
	@ResponseBody
	public Produit getProduitbyid(@PathVariable("id-produit") Long idproduit) {
		return produitServ.AffichDetailProduit(idproduit);

	}
	
	@GetMapping("/get-produit")
	@ResponseBody
	public List<Produit> geProduits() {
		return produitServ.afiichListProduit();

	}
	@PutMapping("/affecter-produit-magasin/{id-magasin}/{id-produit}")
	@ResponseBody
	public Produit affecterproduitmagasin(@PathVariable("id-magasin") Long idmagasin,@PathVariable("id-produit") Long idProd) {
		return produitServ.affecterProduitMagasin(idProd, idmagasin);

	}
	
	
}
