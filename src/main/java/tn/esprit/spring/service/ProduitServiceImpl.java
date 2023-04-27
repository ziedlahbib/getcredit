package tn.esprit.spring.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IProduitservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.MagasinRepository;
import tn.esprit.spring.repository.ProduitRepository;
@Service
public class ProduitServiceImpl implements IProduitservice {

	@Autowired
	ProduitRepository produitRepo;
	@Autowired
	MagasinRepository magasinRepo;
	private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
	
	@Override
	public Produit AjoutProduit(Produit e) {
				Produit p=new Produit();
			 	do{
			 	String s =generateRandomString(8);
			 	Produit pr =produitRepo.findByReference(s);
			 	p=pr;
			 	e.setReference(s);
			 	}
			 	while(p!=null);

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

	@Override
	public Produit affecterProduitMagasin(Long idProduit, Long idMagasin) {
		Magasin m = magasinRepo.findById(idMagasin).orElse(null);
		Produit p =produitRepo.findById(idProduit).orElse(null);
		p.setMagasin(m);
		return produitRepo.save(p);
	}

	@Override
	public List<Produit> afiichListProduitbymagasin(Long idmagasin) {
		Magasin m = magasinRepo.findById(idmagasin).orElse(null);
		return m.getProduits();
	}

}
