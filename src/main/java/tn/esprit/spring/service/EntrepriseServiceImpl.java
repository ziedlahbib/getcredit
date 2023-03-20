package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import Interface.IEntrepriseservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.UserRepository;


public class EntrepriseServiceImpl implements IEntrepriseservice {
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public Entreprise AjoutEntreprise(Entreprise e) {
		return entrepriseRepo.save(e);
	}

	@Override
	public Entreprise UpdateEntreprise(Entreprise e,Long idEntreprise) {
		Entreprise en= entrepriseRepo.findById(idEntreprise).orElse(null);
		en.setAdresse(e.getAdresse());
		en.setNom(e.getNom());
		en.setNumfisc(e.getNumfisc());
		return en;
	}

	@Override
	public void SupprimerEntreprise(Long idEnt) {
		entrepriseRepo.deleteById(idEnt);
		
	}

	@Override
	public Entreprise AffichDetailEntreprise(Long idEnt) {
		return entrepriseRepo.findById(idEnt).orElse(null);
	}

	@Override
	public List<Entreprise> afiichListEntreprise() {
		return entrepriseRepo.findAll();
	}

	@Override
	public User affecteragentauentrprise(Long idUser, Long idEntreprise) {
		User u =userRepo.findById(idUser).orElse(null);
		Entreprise e= entrepriseRepo.findById(idEntreprise).orElse(null);
			u.setEntreprise(e);
		return userRepo.save(u);
	}

	@Override
	public User desaffecteragentauentrprise(Long idUser) {
		User u =userRepo.findById(idUser).orElse(null);
			u.setEntreprise(null);
		return userRepo.save(u);
	}

}
