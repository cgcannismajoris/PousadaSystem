package controle;

import factories.PessoaFactory;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Administrador;
import modelo.Pessoa;
import modelo.Proprietario;

// MB de Usuário Autenticado
@ManagedBean(name = "requestUsuarioAuth")
@RequestScoped
public class RequestUsuarioAuth
{
    // Incluir Bean de Pessoa para preenchimento
    private Pessoa pessoa;
    
    // Bean de Proprietário (só existirá 1)
    private ManageBeanProprietario proprietarioMB;
    
    // Bean de Administrador (só existirá 1)
    private ManageBeanAdmin adminMB;
    
    private Date tempo;                 // data de seção
    private String tipoUsuarioSection;  // seção do tipo de usuário

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
        
        if (pessoa == null) return;
        
        switch (pessoa.getPrivilegio())
        {
            case PessoaFactory.PROPRIETARIO:
            {
                // Instanciar MB de proprietário
                this.proprietarioMB = new ManageBeanProprietario();
                this.proprietarioMB.setProprietario((Proprietario)pessoa);
                
                this.tipoUsuarioSection = "proprietario/proprietario_section";
            } break;
                
            case PessoaFactory.ADMINISTRADOR:
            {
                // Instanciar MB de admin
                this.adminMB = new ManageBeanAdmin();
                this.adminMB.setAdmin((Administrador)pessoa);
                
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

    public String getTipoUsuarioSection()
    {
        return tipoUsuarioSection;
    }

    public ManageBeanProprietario getProprietarioMB()
    {
        return proprietarioMB;
    }

    public void setProprietarioMB(ManageBeanProprietario proprietarioMB)
    {
        this.proprietarioMB = proprietarioMB;
    }

    public ManageBeanAdmin getAdminMB()
    {
        return adminMB;
    }

    public void setAdminMB(ManageBeanAdmin adminMB)
    {
        this.adminMB = adminMB;
    }
}
