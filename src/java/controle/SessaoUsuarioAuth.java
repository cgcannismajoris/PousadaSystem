package controle;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pessoa;

// Classe de Sessão de Usuário Autenticado
@ManagedBean(name="beanSessaoUsuarioAuth")
@SessionScoped
public class SessaoUsuarioAuth
{
    // Incluir Bean de Pessoa para preenchimento
    private Pessoa pessoa;
    private Date tempo; // data de seção

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    public Date getTempo()
    {
        return tempo;
    }

    public void setTempo(Date tempo)
    {
        this.tempo = tempo;
    }
    
}
