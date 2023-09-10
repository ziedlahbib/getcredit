package tn.esprit.spring.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.ICreditservice;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.CreditRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.UserRepository;
@Slf4j

@Service
public class CreditServiceImpl implements ICreditservice {

	@Autowired
	CreditRepository creditRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ProduitRepository produitRepo;
	@Override
	public Credit AjoutCredit(Credit e) {
		long miliseconds = System.currentTimeMillis();
		Date date = new Date(miliseconds);
		e.setDateDebut(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(e.getDateDebut());
		calendar.add(Calendar.MONTH, e.getNbrdumois());
		e.setDateFin(calendar.getTime());
		float m=e.getMontant()/e.getNbrdumois();
		log.info("mon"+m);
		e.setMontantparmois(m);
		return creditRepo.save(e);
	}

	@Override
	public Credit UpdateCredit(Credit e, Long idCredit) {
		Credit c = creditRepo.findById(idCredit).orElse(null);
		c.setDateDebut(c.getDateDebut());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(c.getDateDebut());
		calendar.add(Calendar.MONTH, e.getNbrdumois());
		c.setDateFin(calendar.getTime());
		float m=e.getMontant()/e.getNbrdumois();
		log.info("mon"+m);
		c.setNbrdumois(e.getNbrdumois());
		c.setMontantparmois(m);
		c.setIban(e.getIban());
		c.setMontant(e.getMontant());
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
		System.out.println("sss"+u.getId());
		c.setAgent(u);
		return creditRepo.save(c);
	}

	@Override
	public Credit affecterCreditToProduit(Long idcredit, Long idproduit) {
		// TODO Auto-generated method stub
		Credit c = creditRepo.findById(idcredit).orElse(null);
		Produit p = produitRepo.findById(idproduit).orElse(null);
		c.setProduit(p);
		return creditRepo.save(c);
	}

}
