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
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ArrayList<TipoPagamento> getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(ArrayList<TipoPagamento> tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}