package Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Produit;




public interface IProduitservice {
	public Produit AjoutProduit(Produit e);
	public Produit UpdateProduit(Produit e,Long Produit);
	public void SupprimerProduit(Long idProduit);
	public Produit AffichDetailProduit(Long idProduit );
	public List<Produit> afiichListProduit();
	public Produit affecterProduitMagasin(Long idProduit ,Long idMagasin);
	public List<Produit> afiichListProduitbymagasin(Long idmagasin);

}
