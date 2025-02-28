package Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Credit;



public interface ICreditservice {
	public Credit AjoutCredit(Credit e);
	public Credit UpdateCredit(Credit e,Long idCredit);
	public void SupprimerCredit(Long idCredit);
	public Credit AffichDetailCredit(Long idCredit);
	public List<Credit> afiichListCredit();
	public Credit affectercreditToClient(Long idcredit,Long idclient);
	public Credit affecterCreditToAgent(Long idcredit,Long idAgent);
	public Credit affecterCreditToProduit(Long idcredit,Long idproduit);

}
