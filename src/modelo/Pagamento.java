package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pagamento {
 
    private Integer id;
    private BigDecimal valor;
    private ArrayList<TipoPagamento> tipoPagamento = new ArrayList<TipoPagamento>();

    public Pagamento(Integer id, TipoPagamento tipoPag, BigDecimal valor) {
        this.id = id;
        this.tipoPagamento.add(tipoPag);
        this.valor = valor;
    }

    public Pagamento(Integer id, ArrayList<TipoPagamento> tipoPag, BigDecimal valor) {
        this.id = id;
        this.tipoPagamento.addAll(tipoPag);
        this.valor = valor;
    }
    
    public void efetuarPagamento(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public ReciboDeServico getReciboDeServico() {
        return reciboDeServico;
    }

    public void setReciboDeServico(ReciboDeServico reciboDeServico) {
        this.reciboDeServico = reciboDeServico;
    }

    public TipoPagamento[] getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento[] tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
 
