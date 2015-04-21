package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Equipamento
{
    private Integer id;
    private String descricao;
    private ArrayList<Chale> chales = new ArrayList<>();
    

    public Equipamento(Integer id, String descricao)
    {
        this.id = id;
        this.descricao = descricao;
    }

    public Equipamento(Integer id, String descricao, ArrayList<Chale> chales)
    {
        this.id = id;
        this.descricao = descricao;
        this.chales = chales;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public ArrayList<Chale> getChales()
    {
        return chales;
    }

    public void setChales(ArrayList<Chale> chales)
    {
        this.chales = chales;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.descricao);
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
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao))
        {
            return false;
        }
        return true;
    }
}
