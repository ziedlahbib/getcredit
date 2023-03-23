package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IMagasinservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MagasinRepository;

@Service
public class MagasinServiceImpl implements IMagasinservice {
	@Autowired
	MagasinRepository magasinRepo;
	@Autowired
	EntrepriseRepository entRepo;

	@Override
	public Magasin AjoutMagasin(Magasin e) {
		return magasinRepo.save(e);
	}

	@Override
	public Magasin UpdateMagasin(Magasin e, Long idMagasin) {
		Magasin m = magasinRepo.findById(idMagasin).orElse(null);
		m.setAddresse(e.getAddresse());
		return magasinRepo.save(m);
	}

	@Override
	public void SupprimerMagasin(Long idMagasin) {
		magasinRepo.deleteById(idMagasin);
	}

	@Override
	public Magasin AffichDetailMagasin(Long idMagasin) {

		return magasinRepo.findById(idMagasin).orElse(null);
	}

	@Override
	public List<Magasin> afiichListMagasin() {
		return magasinRepo.findAll();
	}

	@Override
	public Magasin affectermagasinaentreprise(Long idmagasin, Long ident) {
		Magasin m = magasinRepo.findById(idmagasin).orElse(null);
		Entreprise e =entRepo.findById(ident).orElse(null);
		m.setEntreprise(e);
		return magasinRepo.save(m);
	}

	@Override
	public List<Magasin> getlistMagasinparEntreprise(Long idEnt) {
		Entreprise e =entRepo.findById(idEnt).orElse(null);
		return e.getMagasins();
	}

}
