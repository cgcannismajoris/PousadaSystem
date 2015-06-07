package modelo;

import java.util.Objects;

public class Area
{
    private Integer id;
    private String DDD;
    private Estado estado;

    public Area(Integer id, String DDD, Estado estado)
    {
        this.id = id;
        this.DDD = DDD;
        this.id = id;
        this.estado = estado;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDDD()
    {
        return DDD;
    }

    public void setDDD(String DDD)
    {
        this.DDD = DDD;
    }

    public Estado getEstado()
    {
        return estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Area other = (Area) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

}
