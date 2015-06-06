package controle;

import dao.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Pessoa;
import modelo.Proprietario;

@ManagedBean(name="proprietarioMB")
@RequestScoped
public class ManageBeanProprietario
{
    private Proprietario proprietario;
    
    private Pessoa tmpAdministrador;
    private boolean cadAdmin = false;
    
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

    public Pessoa getTmpAdministrador() {
        return tmpAdministrador;
    }

    public void setTmpAdministrador(Pessoa tmpAdministrador) {
        this.tmpAdministrador = tmpAdministrador;
    }

    public boolean isCadAdmin() {
        return cadAdmin;
    }

    public void setCadAdmin(boolean cadAdmin) {
        this.cadAdmin = cadAdmin;
    }
}
