package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IEntrepriseservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MagasinRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseservice {
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	MagasinRepository magasinRepo;
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
		return entrepriseRepo.save(en);
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
	public Entreprise getEntreprisedemagasin(Long idmag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise affecteruserauentreprise(Long iduser, Long ident) {
		User u = userRepo.findById(iduser).orElse(null);
		Entreprise e = entrepriseRepo.findById(ident).orElse(null);
		u.getEntreprise().add(e);
		return entrepriseRepo.save(e);
	}

	@Override
	public List<Entreprise> getEntrepriseparuser(Long idUser) {
		User u =userRepo.findById(idUser).orElse(null);
		return u.getEntreprise();
	}




}
