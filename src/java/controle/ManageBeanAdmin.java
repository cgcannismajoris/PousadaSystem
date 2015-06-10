package controle;

// ManageBean específico para Administrador
import dao.ChaleDAO;
import dao.EquipamentoDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Administrador;
import modelo.Chale;
import modelo.Equipamento;

@ManagedBean(name = "adminMB")
@RequestScoped
public class ManageBeanAdmin
{
    private Administrador admin;

    public final byte PAINEL_PERFIL = 1;
    public final byte PAINEL_GER_EQUIPS = 2;
    public final byte PAINEL_GER_CHALES = 3;
    public final byte PAINEL_GER_CLIENTES = 4;
    public final byte PAINEL_GER_HOSPEDAGENS = 5;

    // Estados de interface de paineis
    private String strExibePerfil = "active";     // "active" ou ""
    private String strExibeGerEquips = "";
    private String strExibeGerChales = "";
    private String strExibeGerClientes = "";
    private String strExibeGerHospedagens = "";

    private boolean boolExibePerfil = true;
    private boolean boolExibeGerEquips;
    private boolean boolExibeGerChales;
    private boolean boolExibeGerClientes;
    private boolean boolExibeGerHospedagens;
    
    // Estados de interface de abas
    // Gerência de Equipamento
    public final byte ABA_GER_EQUIPS_INSERIR_EQUIP = 1;
    public final byte ABA_GER_EQUIPS_VISUAL_EQUIPS = 2;

    private boolean boolExibeInserirEquip = true;
    private boolean boolExibeVisualEquips;

    private String strExibeInserirEquip = "active";
    private String strExibeVisualEquips = "";
    
    // Gerência de Chalés
    public final byte ABA_GER_CHALES_INSERIR_CHALE = 1;
    public final byte ABA_GER_CHALES_VISUAL_CHALES = 2;
    
    private boolean boolExibeInserirChale = true;
    private boolean boolExibeVisualChales;
    
    private String strExibeInserirChale = "active";
    private String strExibeVisualChales = "";
    
    // Gerência de Clientes
    public final byte ABA_GER_CLIENTES_INSERIR_CLIENTE = 1;
    public final byte ABA_GER_CLIENTES_VISUAL_CLIENTES = 2;
    
    private boolean boolExibeInserirCliente = true;
    private boolean boolExibeVisualClientes;
    
    private String strExibeInserirCliente = "active";
    private String strExibeVisualClientes = "";
    

    // Equipamento temporário
    private Equipamento tmpEquip = new Equipamento();
    private ArrayList<Equipamento> tmpEquips = new ArrayList<>();
    
    private Map<Equipamento, Boolean> tmpMapSelectedEquips = new HashMap<>();
    
    // Chalé temporário
    private Chale tmpChale = new Chale();
    private ArrayList<Chale> tmpChales = new ArrayList<>();
    
    public String salvarEquip()
    {
        System.out.println(tmpEquip.getDescricao());

        if (EquipamentoDAO.inserirEquipamentoDAO(tmpEquip))
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Sucesso!",
                            "Sucesso ao inserir."
                    )
            );
            
            this.tmpEquip.setDescricao("");
            this.tmpEquip.setId(0);
            
            return (null);
        } else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Erro!",
                            "Erro ao inserir equipamento."
                    )
            );
            return (null);
        }
    }
    
    public String editarEquip(Equipamento e){
        e.setEditable(true);
        System.out.println("aqui " + e.getDescricao() + "++++++++++++++++++++++++++++++++++++++++++++++++++");
        return ("dashboard");
    }
    
    public String salvarEquip(Equipamento e){
        e.setEditable(false);
        EquipamentoDAO.atualizarEquipamento(e);
        System.out.println("aqui " + e.getDescricao() + "++++++++++++++++++++++++++++++++++++++++++++++++++");
        return ("dashboard");
    }
    
    public String apagarEquip(Equipamento e){
        e.setEditable(false);
        return ("dashboard");
    }
    
    public String salvarChale(){
        
        //Insere os equipamentos selecionados
        for(Map.Entry<Equipamento, Boolean> item : this.tmpMapSelectedEquips.entrySet()){  
            if(item.getValue() == true){
                this.tmpChale.addEquipamento(item.getKey());
            }
        }
        
        if (ChaleDAO.inserirChaleDAO(tmpChale))
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Sucesso!",
                            "Sucesso ao inserir."
                    )
            );
            
            this.tmpChale.setDiaria(new BigDecimal(0.0));
            this.tmpChale.setEquipamentos(new ArrayList<>());
            this.tmpChale.setId(0);
            this.tmpChale.setNumero(0);
            this.tmpEquip.setId(0);
            
            return (null);
        } else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Erro!",
                            "Erro ao inserir equipamento."
                    )
            );
            return (null);
        }
    }
    
    public ArrayList<Equipamento> carregarTodosEquips(){
        this.tmpEquips = EquipamentoDAO.obterTodos();
        return (this.tmpEquips);
    }

    public ArrayList<Equipamento> carregarTodosEquipsForNewChale(){
        this.tmpEquips = EquipamentoDAO.obterTodos();
        
        //Adiciona os equipamentos no mapa
        for(Equipamento item : this.tmpEquips){
            this.tmpMapSelectedEquips.put(item, false);
        }
        
        return (this.tmpEquips);
    }
    
    public ArrayList<Chale> carregarTodosChales(){
        this.tmpChales = ChaleDAO.obterTodos();
        return (this.tmpChales);
    }
	
    public void ativarPainelPerfil(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_PERFIL);
    }

    public void ativarPainelGerEquips(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_GER_EQUIPS);
    }

    public void ativarPainelGerChales(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_GER_CHALES);
    }

    public void ativarPainelGerClientes(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_GER_CLIENTES);
    }
    
    public void ativarPainelGerHospedagens(AjaxBehaviorEvent event)
    {
        this.trocarPainel(PAINEL_GER_HOSPEDAGENS);
    }

    private void trocarPainel(byte painel)
    {
        switch (painel)
        {
            case PAINEL_PERFIL:
            {
                this.strExibePerfil = "active";
                this.strExibeGerEquips = "";
                this.strExibeGerChales = "";
                this.strExibeGerClientes = "";
                this.strExibeGerHospedagens = "";

                this.boolExibePerfil = true;
                this.boolExibeGerEquips = false;
                this.boolExibeGerChales = false;
                this.boolExibeGerClientes = false;
                this.boolExibeGerHospedagens = false;
				
                this.tmpEquips.clear();
                this.tmpChales.clear();
            }
            break;

            case PAINEL_GER_EQUIPS:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "active";
                this.strExibeGerChales = "";
                this.strExibeGerClientes = "";
                this.strExibeGerHospedagens = "";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = true;
                this.boolExibeGerChales = false;
                this.boolExibeGerClientes = false;
                this.boolExibeGerHospedagens = false;
                
                this.tmpChales.clear();
            }
            break;

            case PAINEL_GER_CHALES:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "";
                this.strExibeGerChales = "active";
                this.strExibeGerClientes = "";
                this.strExibeGerHospedagens = "";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = false;
                this.boolExibeGerChales = true;
                this.boolExibeGerClientes = false;
                this.boolExibeGerHospedagens = false;
				
                this.tmpEquips.clear();
            }
            break;

            case PAINEL_GER_CLIENTES:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "";
                this.strExibeGerChales = "";
                this.strExibeGerClientes = "active";
                this.strExibeGerHospedagens = "";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = false;
                this.boolExibeGerChales = false;
                this.boolExibeGerClientes = true;
                this.boolExibeGerHospedagens = false;
				
                this.tmpEquips.clear();
                this.tmpChales.clear();
            }
            break;

            case PAINEL_GER_HOSPEDAGENS:
            {
                this.strExibePerfil = "";
                this.strExibeGerEquips = "";
                this.strExibeGerChales = "";
                this.strExibeGerClientes = "";
                this.strExibeGerHospedagens = "active";

                this.boolExibePerfil = false;
                this.boolExibeGerEquips = false;
                this.boolExibeGerChales = false;
                this.boolExibeGerClientes = false;
                this.boolExibeGerHospedagens = true;
				
                this.tmpEquips.clear();
                this.tmpChales.clear();
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
    
    public void ativarAbaGerChalesInserirChale(AjaxBehaviorEvent event)
    {
        trocarAbaGerChales(ABA_GER_CHALES_INSERIR_CHALE);
    }
    
    public void ativarAbaGerChalesVisualChales(AjaxBehaviorEvent event)
    {
        trocarAbaGerChales(ABA_GER_CHALES_VISUAL_CHALES);
    }
    
    public void ativarAbaGerClientesInserirCliente(AjaxBehaviorEvent event)
    {
        trocarAbaGerClientes(ABA_GER_CLIENTES_INSERIR_CLIENTE);
    }
    
    public void ativarAbaGerClientesVisualChales(AjaxBehaviorEvent event)
    {
        trocarAbaGerClientes(ABA_GER_CLIENTES_VISUAL_CLIENTES);
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
    
    private void trocarAbaGerChales(byte aba)
    {
        switch(aba)
        {
            case ABA_GER_CHALES_INSERIR_CHALE:
            {
                this.strExibeInserirChale = "active";
                this.strExibeVisualChales = "";
                
                this.boolExibeInserirChale = true;
                this.boolExibeVisualChales = false;
            } break;
                
            case ABA_GER_CHALES_VISUAL_CHALES:
            {
                this.strExibeInserirChale = "";
                this.strExibeVisualChales = "active";
                
                this.boolExibeInserirChale = false;
                this.boolExibeVisualChales = true;
            } break;
        }
    }
    
    private void trocarAbaGerClientes(byte aba)
    {
        switch(aba)
        {
            case ABA_GER_CLIENTES_INSERIR_CLIENTE:
            {
                this.strExibeInserirCliente = "active";
                this.strExibeVisualClientes = "";
                
                this.boolExibeInserirCliente = true;
                this.boolExibeVisualClientes = false;
            } break;
                
            case ABA_GER_CLIENTES_VISUAL_CLIENTES:
            {
                this.strExibeInserirCliente = "";
                this.strExibeVisualClientes = "active";
                
                this.boolExibeInserirCliente = false;
                this.boolExibeVisualClientes = true;
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

    public String getStrExibeGerChales()
    {
        return strExibeGerChales;
    }

    public void setStrExibeGerChales(String strExibeGerChales)
    {
        this.strExibeGerChales = strExibeGerChales;
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

    public boolean isBoolExibeGerChales()
    {
        return boolExibeGerChales;
    }

    public void setBoolExibeGerChales(boolean boolExibeGerChales)
    {
        this.boolExibeGerChales = boolExibeGerChales;
    }

    public byte getPAINEL_PERFIL()
    {
        return PAINEL_PERFIL;
    }

    public byte getPAINEL_GER_EQUIPS()
    {
        return PAINEL_GER_EQUIPS;
    }

    public byte getPAINEL_GER_CHALES()
    {
        return PAINEL_GER_CHALES;
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

    public String getStrExibeGerClientes()
    {
        return strExibeGerClientes;
    }

    public void setStrExibeGerClientes(String strExibeGerClientes)
    {
        this.strExibeGerClientes = strExibeGerClientes;
    }

    public String getStrExibeGerHospedagens()
    {
        return strExibeGerHospedagens;
    }

    public void setStrExibeGerHospedagens(String strExibeGerHospedagens)
    {
        this.strExibeGerHospedagens = strExibeGerHospedagens;
    }

    public boolean isBoolExibeGerClientes()
    {
        return boolExibeGerClientes;
    }

    public void setBoolExibeGerClientes(boolean boolExibeGerClientes)
    {
        this.boolExibeGerClientes = boolExibeGerClientes;
    }

    public boolean isBoolExibeGerHospedagens()
    {
        return boolExibeGerHospedagens;
    }

    public void setBoolExibeGerHospedagens(boolean boolExibeGerHospedagens)
    {
        this.boolExibeGerHospedagens = boolExibeGerHospedagens;
    }

    public boolean isBoolExibeInserirChale()
    {
        return boolExibeInserirChale;
    }

    public void setBoolExibeInserirChale(boolean boolExibeInserirChale)
    {
        this.boolExibeInserirChale = boolExibeInserirChale;
    }

    public boolean isBoolExibeVisualChales()
    {
        return boolExibeVisualChales;
    }

    public void setBoolExibeVisualChales(boolean boolExibeVisualChales)
    {
        this.boolExibeVisualChales = boolExibeVisualChales;
    }

    public String getStrExibeInserirChale()
    {
        return strExibeInserirChale;
    }

    public void setStrExibeInserirChale(String strExibeInserirChale)
    {
        this.strExibeInserirChale = strExibeInserirChale;
    }

    public String getStrExibeVisualChales()
    {
        return strExibeVisualChales;
    }

    public void setStrExibeVisualChales(String strExibeVisualChales)
    {
        this.strExibeVisualChales = strExibeVisualChales;
    }

    public boolean isBoolExibeInserirCliente()
    {
        return boolExibeInserirCliente;
    }

    public void setBoolExibeInserirCliente(boolean boolExibeInserirCliente)
    {
        this.boolExibeInserirCliente = boolExibeInserirCliente;
    }

    public boolean isBoolExibeVisualClientes()
    {
        return boolExibeVisualClientes;
    }

    public void setBoolExibeVisualClientes(boolean boolExibeVisualClientes)
    {
        this.boolExibeVisualClientes = boolExibeVisualClientes;
    }

    public String getStrExibeInserirCliente()
    {
        return strExibeInserirCliente;
    }

    public void setStrExibeInserirCliente(String strExibeInserirCliente)
    {
        this.strExibeInserirCliente = strExibeInserirCliente;
    }

    public String getStrExibeVisualClientes()
    {
        return strExibeVisualClientes;
    }

    public void setStrExibeVisualClientes(String strExibeVisualClientes)
    {
        this.strExibeVisualClientes = strExibeVisualClientes;
    }

    public Chale getTmpChale() {
        return tmpChale;
    }

    public void setTmpChale(Chale tmpChale) {
        this.tmpChale = tmpChale;
    }

    public Map<Equipamento, Boolean> getTmpMapSelectedEquips() {
        return tmpMapSelectedEquips;
    }

    public void setTmpMapSelectedEquips(Map<Equipamento, Boolean> tmpMapSelectedEquips) {
        this.tmpMapSelectedEquips = tmpMapSelectedEquips;
    }
}
