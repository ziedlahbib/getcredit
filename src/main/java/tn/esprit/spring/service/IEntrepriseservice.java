package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.User;

public interface IEntrepriseservice {
	public Entreprise AjoutEntreprise(Entreprise e);
	public Entreprise UpdateEntreprise(Entreprise e,Long idEntreprise);
	public void SupprimerEntreprise(Long idEnt);
	public Entreprise AffichDetailEntreprise(Long idEnt);
	public List<Entreprise> afiichListEntreprise();
	public User affecteragentauentrprise(Long idUser,Long idEntreprise);
	public User desaffecteragentauentrprise(Long idUser);
	

}
