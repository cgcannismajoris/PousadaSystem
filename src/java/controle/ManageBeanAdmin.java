package controle;

// ManageBean específico para Administrador
import dao.EquipamentoDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Administrador;
import modelo.Equipamento;

@ManagedBean(name = "adminMB")
@RequestScoped
public class ManageBeanAdmin
{
    private Administrador admin;

    public final byte PAINEL_PERFIL = 1;
    public final byte PAINEL_GER_EQUIPS = 2;
    public final byte PAINEL_GER_CHALE = 3;

    // Estados de interface de paineis
    private String strExibePerfil = "active";     // "active" ou ""
    private String strExibeGerEquips = "";
    private String strExibeGerChale = "";

    private boolean boolExibePerfil = true;
    private boolean boolExibeGerEquips;
    private boolean boolExibeGerChale;

    // Estados de interface de abas
    // Gerência de Equipamento
    public final byte ABA_GER_EQUIPS_INSERIR_EQUIP = 1;
    public final byte ABA_GER_EQUIPS_VISUAL_EQUIPS = 2;

    private boolean boolExibeInserirEquip = true;
    private boolean boolExibeVisualEquips;

    private String strExibeInserirEquip = "active";
    private String strExibeVisualEquips = "";
    
    // Equipamento temporário
    private Equipamento tmpEquip = new Equipamento();
    
    public String salvarEquip()
    {
        System.out.println(tmpEquip.getDescricao());
        
        if (EquipamentoDAO.inserirEquipamentoDAO(tmpEquip))
        {
            FacesContext.getCurrentInstance().addMessage
            (
                null,
                new FacesMessage
                (
                    FacesMessage.SEVERITY_INFO,
                    "Sucesso!",
                    "Sucesso ao inserir."
                )
            );
            tmpEquip = new Equipamento();
              return (null);
        } else
        {
            FacesContext.getCurrentInstance().addMessage
            (
                null,
                new FacesMessage
                (
                    FacesMessage.SEVERITY_ERROR,
                    "Erro!",
                    "Erro ao inserir equipamento."
                )
            );
            return (null);
        }
        
       
    }
    

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
            }
            break;

            case PAINEL_GER_EQUIPS:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "active";
                this.strExibeGerChale = "";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = true;
                this.boolExibeGerChale = false;
            }
            break;
        }
    }
    
    public void ativarAbaGerEquipsInserirEquip(AjaxBehaviorEvent event)
    {
        trocarAbaGerEquip(ABA_GER_EQUIPS_INSERIR_EQUIP);
    }
    
    public void ativarAbaGerEquipsVisualEquips(AjaxBehaviorEvent event)
    {
        trocarAbaGerEquip(ABA_GER_EQUIPS_VISUAL_EQUIPS);
    }

    private void trocarAbaGerEquip(byte aba)
    {
        switch (aba)
        {
            case ABA_GER_EQUIPS_INSERIR_EQUIP:
            {
                this.strExibeInserirEquip = "active";
                this.strExibeVisualEquips = "";

                this.boolExibeInserirEquip = true;
                this.boolExibeVisualEquips = false;
            }
            break;

            case ABA_GER_EQUIPS_VISUAL_EQUIPS:
            {
                this.strExibeInserirEquip = "";
                this.strExibeVisualEquips = "active";

                this.boolExibeInserirEquip = false;
                this.boolExibeVisualEquips = true;
            }
            break;
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

    public boolean isBoolExibeInserirEquip()
    {
        return boolExibeInserirEquip;
    }

    public void setBoolExibeInserirEquip(boolean boolExibeInserirEquip)
    {
        this.boolExibeInserirEquip = boolExibeInserirEquip;
    }

    public boolean isBoolExibeVisualEquips()
    {
        return boolExibeVisualEquips;
    }

    public void setBoolExibeVisualEquips(boolean boolExibeVisualEquips)
    {
        this.boolExibeVisualEquips = boolExibeVisualEquips;
    }

    public String getStrExibeInserirEquip()
    {
        return strExibeInserirEquip;
    }

    public void setStrExibeInserirEquip(String strExibeInserirEquip)
    {
        this.strExibeInserirEquip = strExibeInserirEquip;
    }

    public String getStrExibeVisualEquips()
    {
        return strExibeVisualEquips;
    }

    public void setStrExibeVisualEquips(String strExibeVisualEquips)
    {
        this.strExibeVisualEquips = strExibeVisualEquips;
    }

    public Equipamento getTmpEquip()
    {
        return tmpEquip;
    }

    public void setTmpEquip(Equipamento tmpEquip)
    {
        this.tmpEquip = tmpEquip;
    }
}
