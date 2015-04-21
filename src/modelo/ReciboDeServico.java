package modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ReciboDeServico
{
    private Integer id;
    private Date data;
    private BigDecimal valorTotal;
    
    private Servico servico;

    
    public ReciboDeServico(Integer id, Servico servico,
            Date data,
            BigDecimal valorTotal)
    {
        this.id = id;
        this.data = data;
        this.servico = servico;
        this.valorTotal = valorTotal;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public BigDecimal getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal)
    {
        this.valorTotal = valorTotal;
    }

    public Servico getServico()
    {
        return servico;
    }

    public void setServico(Servico servico)
    {
        this.servico = servico;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.data);
        hash = 29 * hash + Objects.hashCode(this.valorTotal);
        hash = 29 * hash + Objects.hashCode(this.servico);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ReciboDeServico other = (ReciboDeServico) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.data, other.data))
        {
            return false;
        }
        if (!Objects.equals(this.valorTotal, other.valorTotal))
        {
            return false;
        }
        if (!Objects.equals(this.servico, other.servico))
        {
            return false;
        }
        return true;
    }
}
