package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Hospedagem {
    
    public static final int FIMHOSP_SUCCESS = 0;
    public static final int FIMHOSP_INVALIDDATE = 1;
    public static final int FINALIZED = 1;
    public static final int PROGRESS = 0;
    
    private int id;
    private int quantAcomp;
    private Date dataInicio;
    private Date dataSaida;
    private int previsao;
    private Cliente cliente;
    private ArrayList<ReciboDeServico> recibosDeServico;
    private Chale chale;
    private Pagamento pagamento;
    private int flag = Hospedagem.PROGRESS;
    
    public Hospedagem() {
        
    }
    
    public Hospedagem(int quantAcomp, Date dataInicio, int previsao, Cliente cliente, Chale chale) {
        this.quantAcomp = quantAcomp;
        this.dataInicio = dataInicio;
        this.previsao = previsao;
        this.cliente = cliente;
        this.chale = chale;
        this.pagamento = null;
        
    }
    
    public int finalizarHospedagem(Date dataSaida){
        if(this.dataInicio.after(dataInicio)){
            return (FIMHOSP_INVALIDDATE);
        }

        
        return (Hospedagem.FIMHOSP_SUCCESS);
    }

    public BigDecimal calcularCustos(){
        BigDecimal custos = new BigDecimal(0.0);
        
        for(ReciboDeServico i : this.recibosDeServico){
            custos.add(new BigDecimal(i.getValorTotal().toString()));
        }
       
        //adicionar código para aplicar multa
        
        return custos;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantAcomp() {
        return quantAcomp;
    }

    public void setQuantAcomp(int quantAcomp) {
        this.quantAcomp = quantAcomp;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getPrevisao() {
        return previsao;
    }

    public void setPrevisao(int previsao) {
        this.previsao = previsao;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public ReciboDeServico[] getReciboDeServico() {
        return reciboDeServico;
    }

    public void setReciboDeServico(ReciboDeServico[] reciboDeServico) {
        this.reciboDeServico = reciboDeServico;
    }

    public Chale getChale() {
        return chale;
    }

    public void setChale(Chale chale) {
        this.chale = chale;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
 
