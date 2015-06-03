package controle;

import factories.PessoaFactory;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Pessoa;

// Classe de Sessão de Usuário Autenticado
@ManagedBean(name = "beanSessaoUsuarioAuth")
@SessionScoped
public class SessaoUsuarioAuth
{
    // Incluir Bean de Pessoa para preenchimento
    private Pessoa pessoa;
    
    private Date tempo;                 // data de seção
    private String tipoUsuarioSection;  // seção do tipo de usuário

    
    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
        
        switch (pessoa.getPrivilegio())
        {
            case PessoaFactory.PROPRIETARIO:
            {
                this.tipoUsuarioSection = "proprietario/proprietario_section";
            } break;
                
            case PessoaFactory.ADMINISTRADOR:
            {
                this.tipoUsuarioSection = "admin/admin_section";
            } break;
                
            case PessoaFactory.CLIENTE:
            {
                this.tipoUsuarioSection = "cliente/cliente_section";
            } break;
        }
        
    }

    public Date getTempo()
    {
        return tempo;
    }

    public void setTempo(Date tempo)
    {
        this.tempo = tempo;
    }
    
    // Método para Logout
    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        // Indicar que o usuário não está mais autenticado
        ManageBeanUsuario.setAuthUser(false);
        
        return "/index.xhtml?faces-redirect=true";
    }

    public String getTipoUsuarioSection()
    {
        return tipoUsuarioSection;
    }
}
