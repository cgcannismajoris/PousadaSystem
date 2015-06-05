package controle;

import dao.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Proprietario;

@ManagedBean(name="proprietarioMB")
@RequestScoped
public class ManageBeanProprietario
{
    private Proprietario proprietario;
    
    public boolean existeAdmin()
    {
        return UsuarioDAO.obterAdmin() != null;
    }

    public Proprietario getProprietario()
    {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario)
    {
        this.proprietario = proprietario;
    }
}
