package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IcreditRefuseService;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Creditrefuse;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.CreditRefuseRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class CreditRefuseServiceImpl implements IcreditRefuseService{

	@Autowired
	CreditRefuseRepository creditRefuseRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ProduitRepository produitRepo;
	
	@Override
	public Creditrefuse AjoutCredit(Creditrefuse e) {
		long miliseconds = System.currentTimeMillis();
		Date date = new Date(miliseconds);
		e.setDateDebut(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(e.getDateDebut());
		calendar.add(Calendar.MONTH, e.getNbrdumois());
		e.setDateFin(calendar.getTime());
		float m=e.getMontant()/e.getNbrdumois();
		e.setMontantparmois(m);
		return creditRefuseRepo.save(e);
	}
	@Override
	public Creditrefuse UpdateCredit(Creditrefuse e, Long idCredit) {
		Creditrefuse c = creditRefuseRepo.findById(idCredit).orElse(null);
		c.setDateDebut(e.getDateDebut());
		c.setDateFin(e.getDateFin());
		c.setMontant(e.getMontant());
		c.setMontantparmois(e.getMontantparmois());
		c.setRestapayer(e.getRestapayer());
		c.setIban(e.getIban());
		return creditRefuseRepo.save(c);
	}
	@Override
	public Creditrefuse AffichDetailCredit(Long idCredit) {
		return creditRefuseRepo.findById(idCredit).orElse(null);
	}

	@Override
	public List<Creditrefuse> afiichListCredit() {
		return creditRefuseRepo.findAll();
	}

	@Override
	public Creditrefuse affectercreditToClient(Long idcredit, Long idclient) {
		Creditrefuse c = creditRefuseRepo.findById(idcredit).orElse(null);
		User u = userRepo.findById(idclient).orElse(null);
		c.setClient(u);
		return creditRefuseRepo.save(c);
	}

	@Override
	public Creditrefuse affecterCreditToAgent(Long idcredit, Long idAgent) {
		Creditrefuse c = creditRefuseRepo.findById(idcredit).orElse(null);
		User u = userRepo.findById(idAgent).orElse(null);
		c.setAgentr(u);
		return creditRefuseRepo.save(c);
	}

	@Override
	public Creditrefuse affecterCreditToProduit(Long idcredit, Long idproduit) {
		// TODO Auto-generated method stub
		Creditrefuse c = creditRefuseRepo.findById(idcredit).orElse(null);
		Produit p = produitRepo.findById(idproduit).orElse(null);
		c.setProduit(p);
		return creditRefuseRepo.save(c);
	}

}
