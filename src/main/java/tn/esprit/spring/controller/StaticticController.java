package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Creditrefuse;
import tn.esprit.spring.service.StatistiqueService;

@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/statistic")
public class StaticticController {
	@Autowired
	StatistiqueService statserv;
	@GetMapping("/get-creditref-pourcentage")
	@ResponseBody
	public float getcreditrefpourc() {
		return statserv.pourcentagecrdref();

	}
	@GetMapping("/get-credit-pourcentage")
	@ResponseBody
	public float getcreditpourc() {
		return statserv.pourcentagecrd();

	}
	@GetMapping("/get-creditref-ent/{id-ent}")
	@ResponseBody
	public float nbrcreditrefparent(@PathVariable("id-ent") Long ident) {
		return statserv.nbrcreditrefparent(ident);

	}
	@GetMapping("/get-credit-ent/{id-ent}")
	@ResponseBody
	public float nbrcreditparent(@PathVariable("id-ent") Long ident) {
		return statserv.nbrcreditparent(ident);

	}
	@GetMapping("/get-creditref-mag/{id-mag}")
	@ResponseBody
	public float nbrcreditrefpamag(@PathVariable("id-mag") Long idmag) {
		return statserv.nbrcreditrefparmag(idmag);

	}
	@GetMapping("/get-credit-mag/{id-mag}")
	@ResponseBody
	public float nbrcreditpamag(@PathVariable("id-mag") Long idmag) {
		return statserv.nbrcreditparmag(idmag);

	}
}
