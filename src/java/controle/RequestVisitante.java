package controle;

// Bean de RequestVisitante do Site

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="requestVisitante")
@RequestScoped
public class RequestVisitante
{
    private String login;
    private String senha;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
    public String invalidarSessao(String pagina)
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return pagina;
    }
}
