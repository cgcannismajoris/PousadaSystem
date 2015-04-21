package modelo;

import java.util.Date;
import java.util.Objects;

public class Reserva
{
    private int id;
    private Date dataInicio;
    private Date dataFim;

    private Cliente cliente;
    private Chale chale;

    
    public Reserva(int id, Date dataInicio, Date dataFim,
            Cliente cliente,
            Chale chale)
    {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.chale = chale;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Chale getChale()
    {
        return chale;
    }

    public void setChale(Chale chale)
    {
        this.chale = chale;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.dataInicio);
        hash = 71 * hash + Objects.hashCode(this.dataFim);
        hash = 71 * hash + Objects.hashCode(this.cliente);
        hash = 71 * hash + Objects.hashCode(this.chale);
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
        final Reserva other = (Reserva) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio))
        {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim))
        {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente))
        {
            return false;
        }
        if (!Objects.equals(this.chale, other.chale))
        {
            return false;
        }
        return true;
    }
}
