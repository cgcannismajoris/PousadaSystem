package listeners;

import controle.SessaoUsuario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class UserAuthListener implements PhaseListener
{

    @Override
    public void afterPhase(PhaseEvent pe)
    {
        FacesContext facesContext = pe.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        boolean isLoginPage = (currentPage.lastIndexOf("login") > -1);
        boolean isMyCP = (currentPage.lastIndexOf("mycontrolpanel") > -1);
        boolean isIndexPage = (currentPage.lastIndexOf("index") > -1);

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        SessaoUsuario sessaoUsuario = (SessaoUsuario) session.getAttribute("sessaoUsuario");

        if (sessaoUsuario != null)
        {
            if (sessaoUsuario.getVisitante() == null)
            {
                System.out.println("VISITANTE: AINDA NAO CRIADO");
            } else
            {
                System.out.println("VISITANTE: JA CRIADO");
            }

            if (sessaoUsuario.getUsuarioAuth() == null)
            {
                System.out.println("USUARIO_AUTH: AINDA NAO CRIADO");
            } else
            {
                System.out.println("USUARIO_AUTH: JA CRIADO");
            }

            if (isLoginPage)
            {
                if (sessaoUsuario.getUsuarioAuth() != null)
                {
                    NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                    nh.handleNavigation(facesContext, null, "index");
                }
            }

            if (isMyCP)
            {
                SessaoUsuario.navbarType = "static";

                // Caso não exista usuário autenticado
                if (sessaoUsuario.getUsuarioAuth() == null)
                {
                    NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                    nh.handleNavigation(facesContext, null, "index");
                } else // Caso exista usuário autenticado
                {
                    // Caso proprietário esteja autenticado
                    if (sessaoUsuario.getUsuarioAuth().getProprietarioMB() != null)
                    {
                        // Verifica se exista administrador cadastrado
                        sessaoUsuario.getUsuarioAuth().getProprietarioMB().verificaExistAdmin();
                    }
                }
            }

            if (isIndexPage)
            {
                if (SessaoUsuario.navbarType != null);
                    SessaoUsuario.navbarType = "fixed";
            }

        } else
        {
            System.out.println("Sessão de Usuário ainda não criada.");
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe)
    {

    }

    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RESTORE_VIEW;
    }

}
