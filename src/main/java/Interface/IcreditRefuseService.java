package Interface;

import java.util.List;

import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.entity.Creditrefuse;

public interface IcreditRefuseService {
	public Creditrefuse AjoutCredit(Creditrefuse e);
	public Creditrefuse UpdateCredit(Credit e, Long idCredit);
	public Creditrefuse AffichDetailCredit(Long idCredit);
	public List<Creditrefuse> afiichListCredit();
	public Creditrefuse affectercreditToClient(Long idcredit,Long idclient);
	public Creditrefuse affecterCreditToAgent(Long idcredit,Long idAgent);
	public Creditrefuse affecterCreditToProduit(Long idcredit,Long idproduit);

}
