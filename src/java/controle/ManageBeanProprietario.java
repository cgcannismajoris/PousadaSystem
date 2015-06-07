package controle;

import dao.UsuarioDAO;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Administrador;
import modelo.Pessoa;
import modelo.Proprietario;

@ManagedBean(name = "proprietarioMB")
@RequestScoped
public class ManageBeanProprietario
{

    private Proprietario proprietario;

    private Administrador tmpAdministrador;
    private boolean existeAdmin;
    private boolean cadAdminCompleto; // flag para completude de cadastro de admin
    private String loadingFlag = "loading";
    private String bsWizardStep2Flag = "disabled";

    public void verificaExistAdmin()
    {
        if (UsuarioDAO.obterAdmin() == null)
        {
            this.tmpAdministrador = new Administrador();

            this.tmpAdministrador.setNome("Carlos da Silva Rocha Salgado");
            this.tmpAdministrador.setEndereco("Rua Caixas Pretas, Bairro Portão Roxo, "
                    + "Cidade de Vassourinhas");

            this.tmpAdministrador.setDataNascimento(new Date("1980/12/10")); // ISO 8601
            this.tmpAdministrador.setLogin("carlaovagabundo");
            this.tmpAdministrador.setSenha("carlao22doido");

            this.existeAdmin = false;
        } else
        {
            this.existeAdmin = true;
        }
    }

    // Método para sinalizar que o cadastro foi completado
    // após a validação dos campos de dados de Administrador
    public String completarCadastroAdmin()
    {
        if (inserirAdminBD())
        {
            this.loadingFlag = "";
            this.bsWizardStep2Flag = "complete";

            cadAdminCompleto = true;
        } else
        {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Falha ao salvar Administrador.", "Falha ao salvar Administrador no Banco de Dados."));
        }

        return "dashboard";
    }

    // Salvar Administrador criado no BD
    private boolean inserirAdminBD()
    {
        return UsuarioDAO.inserirPessoaDAO((Pessoa) tmpAdministrador);
    }

    // Método para indicar que o cadastro não foi completado?
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

    public boolean isExisteAdmin()
    {
        return existeAdmin;
    }

    public void setExisteAdmin(boolean existeAdmin)
    {
        this.existeAdmin = existeAdmin;
    }

    public boolean isCadAdminCompleto()
    {
        return cadAdminCompleto;
    }

    public void setCadAdminCompleto(boolean cadAdminCompleto)
    {
        this.cadAdminCompleto = cadAdminCompleto;
    }

    public String getLoadingFlag()
    {
        return loadingFlag;
    }

    public void setLoadingFlag(String loadingFlag)
    {
        this.loadingFlag = loadingFlag;
    }

    public String getBsWizardStep2Flag()
    {
        return bsWizardStep2Flag;
    }

    public void setBsWizardStep2Flag(String bsWizardStep2Flag)
    {
        this.bsWizardStep2Flag = bsWizardStep2Flag;
    }
}
