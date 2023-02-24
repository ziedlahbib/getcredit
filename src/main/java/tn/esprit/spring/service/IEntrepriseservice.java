package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Entreprise;

public interface IEntrepriseservice {
	public Entreprise AjoutEntreprise(Entreprise e);
	public Entreprise UpdateEntreprise(Entreprise e);
	public void SupprimerEntreprise(Long idEnt);
	public Entreprise AffichDetailEntreprise(Long idEnt);
	public List<Entreprise> afiichListEntreprise();

}
