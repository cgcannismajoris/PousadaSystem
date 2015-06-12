package controle;

import dao.HospedagemDAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Hospedagem;

// ManageBean de Unidade de Testes

@ManagedBean(name="beanTeste")
@RequestScoped
public class ManageBeanTeste
{
    public void testar()
    {
        Hospedagem h = new Hospedagem();
        h.setId(1);
        h.setDataSaida(new Date());
        
        HospedagemDAO.finalizarHospDAO(h);
    }
}
