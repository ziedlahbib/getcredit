package Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Magasin;



public interface IMagasinservice {
	public Magasin AjoutMagasin(Magasin e);
	public Magasin UpdateMagasin(Magasin e,Long idMagasin);
	public void SupprimerMagasin(Long idMagasin);
	public Magasin AffichDetailMagasin(Long idMagasin);
	public List<Magasin> afiichListMagasin();
	public Magasin affectermagasinaentreprise(Long idmagasin,Long iduser);
	public List<Magasin> getlistMagasinparEntreprise(Long idEnt);
	public Magasin getmagasinbyagent(Long idUser);
	public Magasin getmagasinbyproduit(Long idproduit);
	public List<Magasin> getListMagasinByUser(Long idUser);
	public List<Magasin> getListMagasinByENtrepreneur(Long idUser);


}
