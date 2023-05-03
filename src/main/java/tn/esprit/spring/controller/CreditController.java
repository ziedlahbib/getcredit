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

import Interface.IMagasinservice;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Magasin;
import tn.esprit.spring.service.CreditServiceImpl;

@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/credit")
public class CreditController {
	@Autowired
	CreditServiceImpl creditServ;
	
	@PostMapping("/add-credit")
	@ResponseBody
	public Credit addcredit(@RequestBody Credit u) {
		return creditServ.AjoutCredit(u);

	}
	
	@GetMapping("/get-credit/{id-credit}")
	@ResponseBody
	public Credit getmagasinbyid(@PathVariable("id-credit") Long idcredit) {
		return creditServ.AffichDetailCredit(idcredit);

	}
	
	@GetMapping("/get-Credits")
	@ResponseBody
	public List<Credit> getCredits() {
		return creditServ.afiichListCredit();

	}
	@PutMapping("/affecter-credit-client/{id-credit}/{id-client}")
	@ResponseBody
	public Credit affecterCreditToClient(@PathVariable("id-credit") Long idcredit,@PathVariable("id-client") Long idclient) {
		return creditServ.affectercreditToClient(idcredit, idclient);

	}
	@PutMapping("/affecter-credit-client/{id-credit}/{id-agent}")
	@ResponseBody
	public Credit affecterCreditToAgent(@PathVariable("id-credit") Long idcredit,@PathVariable("id-agent") Long idagent) {
		return creditServ.affecterCreditToAgent(idcredit, idagent);

	}

}
