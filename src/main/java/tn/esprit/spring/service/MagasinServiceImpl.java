package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IMagasinservice;
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MagasinRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class MagasinServiceImpl implements IMagasinservice {
	@Autowired
	MagasinRepository magasinRepo;
	@Autowired
	EntrepriseRepository entRepo;
	
	@Autowired
	UserRepository userRepo;

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

	@Override
	public Magasin getmagasinbyagent(Long idUser) {
		User u = userRepo.findById(idUser).orElse(null);
		return u.getMagasin();
	}

	@Override
	public List<Magasin> getListMagasinByUser(Long idUser) {
		User u = userRepo.findById(idUser).orElse(null);
		
		return u.getMagasin().getEntreprise().getMagasins();
	}

	@Override
	public List<Magasin> getListMagasinByENtrepreneur(Long idUser) {
		User u = userRepo.findById(idUser).orElse(null);
		List<Magasin> lsm = new ArrayList<Magasin>();
		for(Entreprise e : u.getEntreprise()) {
			for(Magasin m : e.getMagasins()) {
				lsm.add(m);
			}
			
		}
		return lsm;
	}

}
