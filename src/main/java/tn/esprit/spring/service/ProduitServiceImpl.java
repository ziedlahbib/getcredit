package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Interface.IProduitservice;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ProduitRepository;

public class ProduitServiceImpl implements IProduitservice {

	@Autowired
	ProduitRepository produitRepo;
	@Override
	public Produit AjoutProduit(Produit e) {
		return produitRepo.save(e);
	}

	@Override
	public Produit UpdateProduit(Produit e, Long idProduit) {
		Produit p=produitRepo.findById(idProduit).orElse(null);
		p.setNom(e.getNom());
		p.setPrix(e.getPrix());
		return produitRepo.save(p);
	}

	@Override
	public void SupprimerProduit(Long idProduit) {
		produitRepo.deleteById(idProduit);
		
	}

	@Override
	public Produit AffichDetailProduit(Long idProduit) {

		return produitRepo.findById(idProduit).orElse(null);
	}

	@Override
	public List<Produit> afiichListProduit() {
		// TODO Auto-generated method stub
		return produitRepo.findAll();
	}

}
