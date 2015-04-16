package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class ReciboDeServico {

    private Integer id;
    private Date data;
    private BigDecimal valorTotal;
    private Servico servico;
    
    public ReciboDeServico(Integer id, Servico servico, Date data, BigDecimal valorTotal) {
        this.id = id;
        this.data = data;
        this.servico = servico;
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
 
