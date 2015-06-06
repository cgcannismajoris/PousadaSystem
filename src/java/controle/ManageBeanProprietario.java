package controle;

import dao.UsuarioDAO;
import java.sql.Time;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Administrador;
import modelo.Pessoa;
import modelo.Proprietario;

@ManagedBean(name="proprietarioMB")
@RequestScoped
public class ManageBeanProprietario
{
    private Proprietario proprietario;
    
    private Administrador tmpAdministrador;
    
    public boolean existeAdmin()
    {
        if (UsuarioDAO.obterAdmin() == null)
        {
            this.tmpAdministrador = new Administrador();
            
            this.tmpAdministrador.setNome("Carlos da Silva Rocha Salgado");
            this.tmpAdministrador.setEndereco("Rua Caixas Pretas, Bairro Portão Roxo, "
                    + "Cidade de Vassourinhas");

            this.tmpAdministrador.setLogin("carlaovagabundo");
            this.tmpAdministrador.setSenha("carlao22doido");
            
            return false;
        }
        
        return true; 
    }

    public Proprietario getProprietario()
    {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario)
    {
        this.proprietario = proprietario;
    }

    public Administrador getTmpAdministrador()
    {
        return tmpAdministrador;
    }

    public void setTmpAdministrador(Administrador tmpAdministrador)
    {
        this.tmpAdministrador = tmpAdministrador;
    }
}
