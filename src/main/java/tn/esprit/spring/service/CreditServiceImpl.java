package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.ICreditservice;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.CreditRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class CreditServiceImpl implements ICreditservice {

	@Autowired
	CreditRepository creditRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public Credit AjoutCredit(Credit e) {
		return creditRepo.save(e);
	}

	@Override
	public Credit UpdateCredit(Credit e, Long idCredit) {
		Credit c=creditRepo.findById(idCredit).orElse(null);
		c.setDateDebut(e.getDateDebut());
		c.setDateFin(e.getDateFin());
		c.setMontant(e.getMontant());
		c.setMontantparmois(e.getMontantparmois());
		c.setRestapayer(e.getRestapayer());
		return creditRepo.save(c);
	}

	@Override
	public void SupprimerCredit(Long idCredit) {
		creditRepo.deleteById(idCredit);
		
	}

	@Override
	public Credit AffichDetailCredit(Long idCredit) {
		
		return creditRepo.findById(idCredit).orElse(null);
	}

	@Override
	public List<Credit> afiichListCredit() {
		return creditRepo.findAll();
	}

	@Override
	public Credit affectercreditToClient(Long idcredit, Long idclient) {
		Credit c = creditRepo.findById(idcredit).orElse(null);
		User u = userRepo.findById(idclient).orElse(null);
		c.setUser(u);
		return creditRepo.save(c);
	}

	@Override
	public Credit affecterCreditToAgent(Long idcredit, Long idAgent) {
		Credit c = creditRepo.findById(idcredit).orElse(null);
		User u = userRepo.findById(idAgent).orElse(null);
		c.setAgent(u);
		return creditRepo.save(c);
	}

}
