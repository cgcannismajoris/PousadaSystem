package controle;

import dao.UsuarioDAO;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Pessoa;


@ManagedBean(name="sessaoUsuario")
@SessionScoped
public class SessaoUsuario
{
    // Usuário Autenticado
    private RequestUsuarioAuth usuarioAuth = null;

    @ManagedProperty(value="#{ requestVisitante }")
    private RequestVisitante visitante;
    
    // Estilo de navbar (fixed-top ou static-top)
    public static String navbarType;

    public SessaoUsuario()
    {
        navbarType = "fixed";
    }
    
    public void obterDadosProprietario(AjaxBehaviorEvent event)
    {
        this.getVisitante().setLogin("jorjao");
        this.getVisitante().setSenha("jorjao123");
    }
    
    public void obterDadosAdmin(AjaxBehaviorEvent event)
    {
        this.getVisitante().setLogin("carlaoAdmin");
        this.getVisitante().setSenha("carlao22");
    }
    
    public String logar()
    {
        Pessoa pessoa = UsuarioDAO.autenticarUsuario(visitante);
        
        if (pessoa != null)
        {
            // Alimentar o Usuário a ser autenticado
            this.usuarioAuth = new RequestUsuarioAuth();
            this.usuarioAuth.setPessoa(pessoa);
            this.usuarioAuth.setTempo(new GregorianCalendar().getTime());
            
            // Invalidar visitante
            this.visitante.setLogin(null);
            this.visitante.setSenha(null);
            this.visitante = null;
            
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
    
    // Método para Logout
    public String logout()
    {
        // Visitante é criado novamente (nenhum usuário está autenticado)
        this.visitante = new RequestVisitante();
        this.usuarioAuth = null;
        
        return "/index.xhtml?faces-redirect=true";
    }

    public String getNavbarType()
    {
        return navbarType;
    }

    public void setNavbarType(String type)
    {
        navbarType = type;
    }

    public RequestUsuarioAuth getUsuarioAuth()
    {
        return usuarioAuth;
    }

    public void setUsuarioAuth(RequestUsuarioAuth usuarioAuth)
    {
        this.usuarioAuth = usuarioAuth;
    }

    public RequestVisitante getVisitante()
    {
        return visitante;
    }

    public void setVisitante(RequestVisitante visitante)
    {
        this.visitante = visitante;
    }
}
