package controle;

// ManageBean específico para Administrador

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Administrador;

@ManagedBean(name="adminMB")
@RequestScoped
public class ManageBeanAdmin
{
    private Administrador admin;

    public Administrador getAdmin()
    {
        return admin;
    }

    public void setAdmin(Administrador admin)
    {
        this.admin = admin;
    }
    
}
