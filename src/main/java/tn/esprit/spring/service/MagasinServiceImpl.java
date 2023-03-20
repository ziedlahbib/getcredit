package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Interface.IMagasinservice;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.repository.MagasinRepository;


public class MagasinServiceImpl implements IMagasinservice {
	@Autowired
	MagasinRepository magasinRepo;

	@Override
	public Magasin AjoutMagasin(Magasin e) {
		return magasinRepo.save(e);
	}

	@Override
	public Magasin UpdateMagasin(Magasin e, Long idMagasin) {
		Magasin m = magasinRepo.findById(idMagasin).orElse(null);
		m.setAddresse(e.getAddresse());
		return m;
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

}
