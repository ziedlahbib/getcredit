package Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Magasin;


@Service
public interface IMagasinservice {
	public Magasin AjoutMagasin(Magasin e);
	public Magasin UpdateMagasin(Magasin e,Long idMagasin);
	public void SupprimerMagasin(Long idMagasin);
	public Magasin AffichDetailMagasin(Long idMagasin);
	public List<Magasin> afiichListMagasin();


}
