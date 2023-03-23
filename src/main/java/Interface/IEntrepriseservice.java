package Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.User;

public interface IEntrepriseservice {
	public Entreprise AjoutEntreprise(Entreprise e);
	public Entreprise UpdateEntreprise(Entreprise e,Long idEntreprise);
	public void SupprimerEntreprise(Long idEnt);
	public Entreprise AffichDetailEntreprise(Long idEnt);
	public List<Entreprise> afiichListEntreprise();
	public Entreprise getEntreprisedemagasin(Long idmag);
	public Entreprise affecteruserauentreprise(Long iduser,Long ident);


	

}
