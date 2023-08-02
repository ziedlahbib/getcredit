package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Creditrefuse;
import tn.esprit.spring.service.CreditRefuseServiceImpl;
import tn.esprit.spring.service.CreditServiceImpl;

@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/creditRef")
public class CreditRefuseController {
	@Autowired
	CreditRefuseServiceImpl creditrefServ;
	@PostMapping("/add-creditref")
	@ResponseBody
	public Creditrefuse addcredit(@RequestBody Creditrefuse u) {
		return creditrefServ.AjoutCredit(u);

	}
	@GetMapping("/get-creditref/{id-credit}")
	@ResponseBody
	public Creditrefuse getmagasinbyid(@PathVariable("id-credit") Long idcredit) {
		return creditrefServ.AffichDetailCredit(idcredit);

	}
	
	@GetMapping("/get-Creditsref")
	@ResponseBody
	public List<Creditrefuse> getCredits() {
		return creditrefServ.afiichListCredit();

	}
	@PutMapping("/affecter-creditref-client/{id-credit}/{id-client}")
	@ResponseBody
	public Creditrefuse affecterCreditToClient(@PathVariable("id-credit") Long idcredit,@PathVariable("id-client") Long idclient) {
		return creditrefServ.affectercreditToClient(idcredit, idclient);

	}
	@PutMapping("/affecter-creditref-agent/{id-credit}/{id-agent}")
	@ResponseBody
	public Creditrefuse affecterCreditToAgent(@PathVariable("id-credit") Long idcredit,@PathVariable("id-agent") Long idagent) {
		return creditrefServ.affecterCreditToAgent(idcredit, idagent);

	}
	@PutMapping("/affecter-creditref-produit/{id-credit}/{id-produit}")
	@ResponseBody
	public Creditrefuse affecterCreditToProduit(@PathVariable("id-credit") Long idcredit,@PathVariable("id-produit") Long idproduit) {
		return creditrefServ.affecterCreditToProduit(idcredit, idproduit);

	}

}
