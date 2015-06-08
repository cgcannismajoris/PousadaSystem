package controle;

// ManageBean específico para Administrador
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Administrador;

@ManagedBean(name = "adminMB")
@RequestScoped
public class ManageBeanAdmin
{

    private Administrador admin;

    public final byte PAINEL_PERFIL = 1;
    public final byte PAINEL_GER_EQUIPS = 2;
    public final byte PAINEL_GER_CHALE = 3;

    private String strExibePerfil = "active";     // "active" ou ""
    private String strExibeGerEquips = "";
    private String strExibeGerChale = "";

    private boolean boolExibePerfil = true;
    private boolean boolExibeGerEquips;
    private boolean boolExibeGerChale;
    
    public void ativarPainelPerfil(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_PERFIL);
    }
    
    public void ativarPainelGerEquips(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_GER_EQUIPS);
    }

    private void trocarPainel(byte painel)
    {
        switch (painel)
        {
            case PAINEL_PERFIL:
            {
                this.strExibePerfil = "active";
                this.strExibeGerEquips = "";
                this.strExibeGerChale = "";

                this.boolExibePerfil = true;
                this.boolExibeGerEquips = false;
                this.boolExibeGerChale = false;
            } break;

            case PAINEL_GER_EQUIPS:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "active";
                this.strExibeGerChale = "";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = true;
                this.boolExibeGerChale = false;
            } break;
        }
    }

    public Administrador getAdmin()
    {
        return admin;
    }

    public void setAdmin(Administrador admin)
    {
        this.admin = admin;
    }

    public String getStrExibePerfil()
    {
        return strExibePerfil;
    }

    public void setStrExibePerfil(String strExibePerfil)
    {
        this.strExibePerfil = strExibePerfil;
    }

    public String getStrExibeGerEquips()
    {
        return strExibeGerEquips;
    }

    public void setStrExibeGerEquips(String strExibeGerEquips)
    {
        this.strExibeGerEquips = strExibeGerEquips;
    }

    public String getStrExibeGerChale()
    {
        return strExibeGerChale;
    }

    public void setStrExibeGerChale(String strExibeGerChale)
    {
        this.strExibeGerChale = strExibeGerChale;
    }

    public boolean isBoolExibePerfil()
    {
        return boolExibePerfil;
    }

    public void setBoolExibePerfil(boolean boolExibePerfil)
    {
        this.boolExibePerfil = boolExibePerfil;
    }

    public boolean isBoolExibeGerEquips()
    {
        return boolExibeGerEquips;
    }

    public void setBoolExibeGerEquips(boolean boolExibeGerEquips)
    {
        this.boolExibeGerEquips = boolExibeGerEquips;
    }

    public boolean isBoolExibeGerChale()
    {
        return boolExibeGerChale;
    }

    public void setBoolExibeGerChale(boolean boolExibeGerChale)
    {
        this.boolExibeGerChale = boolExibeGerChale;
    }

    public byte getPAINEL_PERFIL()
    {
        return PAINEL_PERFIL;
    }

    public byte getPAINEL_GER_EQUIPS()
    {
        return PAINEL_GER_EQUIPS;
    }

    public byte getPAINEL_GER_CHALE()
    {
        return PAINEL_GER_CHALE;
    }
}
