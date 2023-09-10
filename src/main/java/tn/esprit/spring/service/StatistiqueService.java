package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Creditrefuse;
import tn.esprit.spring.repository.CreditRefuseRepository;
import tn.esprit.spring.repository.CreditRepository;

@Slf4j

@Service
public class StatistiqueService {

	@Autowired
	CreditRefuseRepository creditRefuseRepo;
	@Autowired
	CreditRepository creditRepo;
	public float pourcentagecrdref() {
		return  creditRefuseRepo.findAll().size();
//		int cr=creditRepo.findAll().size();	
//		log.info("p"+(crf*100)/(crf+cr));
//		return (crf*100)/(crf+cr);
		
	}
	public float pourcentagecrd() {
//		int crf= creditRefuseRepo.findAll().size();
//		int cr=creditRepo.findAll().size();	
//		log.info("p"+(cr*100)/(crf+cr));
//		return (cr*100)/(crf+cr);
		return creditRepo.findAll().size();
		
	}
	public float nbrcreditrefparent(Long ident) {
		List<Creditrefuse> crf= creditRefuseRepo.findAll();
		int n=0;
		for(Creditrefuse cf:crf) {
				if(cf.getProduit().getMagasin().getEntreprise().getEntrpriseId()==ident) {
					n++;
					log.info("nbrcrditent"+n);
				}
			
			
		}
		return n;
		
	}
	public float nbrcreditparent(Long ident) {
		List<Credit> c= creditRepo.findAll();
		int n=0;
		for(Credit cr:c) {
				if(cr.getProduit().getMagasin().getEntreprise().getEntrpriseId()==ident) {
					n++;
					log.info("nbrcrditent"+n);
				}
			
			
		}
		return n;
		
	}
	public float nbrcreditrefparmag(Long idmag) {
		List<Creditrefuse> crf= creditRefuseRepo.findAll();
		int n=0;
		for(Creditrefuse cf:crf) {
				if(cf.getProduit().getMagasin().getMagasinId()==idmag) {
					n++;
					log.info("nbrcrditent"+n);
				}
			
			
		}
		return n;
		
	}
	public float nbrcreditparmag(Long idmag) {
		List<Credit> crf= creditRepo.findAll();
		int n=0;
		for(Credit cf:crf) {
				if(cf.getProduit().getMagasin().getMagasinId()==idmag) {
					n++;
					log.info("nbrcrditent"+n);
				}
			
			
		}
		return n;
		
	}
}
