package controle;

import dao.UsuarioDAO;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Pessoa;
import modelo.Visitante;


// ManagedBean para usuário global (incluso visitante)
@ManagedBean(name="usuarioBean")
@RequestScoped
public class ManageBeanUsuario
{
    // Injeção de Dependência de Sessão de Visitante
    @ManagedProperty(value="#{ beanVisitante }")
    private Visitante visitante;
    
    // Injeção de Dependência de Sessão de Usuário Autenticado
    @ManagedProperty(value="#{ beanSessaoUsuarioAuth }")
    private SessaoUsuarioAuth sessaoUsuarioAuth;
    
    private static boolean authUser;       // usuário autenticado ou não
    
    public void obterDadosProprietario(AjaxBehaviorEvent event)
    {
        visitante.setLogin("jorjao");
        visitante.setSenha("jorjao123");
    }
    
    public String logar()
    {
        String navegacao;
        Pessoa pessoa = UsuarioDAO.autenticarUsuario(visitante);
        
        if (pessoa != null)
        {
            // Alimentar os dados de Sessão de Usuário Autenticado
            this.sessaoUsuarioAuth.setPessoa(pessoa);
            this.sessaoUsuarioAuth.setTempo(new GregorianCalendar().getTime());
            
            // Indicar que o usuário foi autenticado
            authUser = true;
            
            return UsuarioDAO.SUCESSO_LOGIN;
        } else
        {
            FacesContext.getCurrentInstance().addMessage
            (
                null,
                new FacesMessage
                (
                    FacesMessage.SEVERITY_ERROR,
                    "Falha ao realizar login!",
                    "Usuário não cadastrado."
                )
            );
        }
        
        return UsuarioDAO.FALHA_LOGIN;
    }

    public Visitante getVisitante()
    {
        return visitante;
    }

    public void setVisitante(Visitante visitante)
    {
        this.visitante = visitante;
    }

    public SessaoUsuarioAuth getSessaoUsuarioAuth()
    {
        return sessaoUsuarioAuth;
    }

    public void setSessaoUsuarioAuth(SessaoUsuarioAuth sessaoUsuarioAuth)
    {
        this.sessaoUsuarioAuth = sessaoUsuarioAuth;
    }
    
    
    public boolean isAuthUser()
    {
        return authUser;
    }

    public static void setAuthUser(boolean autenticacao)
    {
        authUser = autenticacao;
    }
    
}
