package modelo;

import java.util.Objects;

public class Rua
{
    private Integer id;
    private String CEP;
    private String nome;
    
    private Bairro bairro;

    
    public Rua(String nome, String CEP, Integer id, Bairro bairro)
    {
        this.id = id;
        this.nome = nome;
        this.CEP = CEP;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCEP()
    {
        return CEP;
    }

    public void setCEP(String CEP)
    {
        this.CEP = CEP;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Bairro getBairro()
    {
        return bairro;
    }

    public void setBairro(Bairro bairro)
    {
        this.bairro = bairro;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Rua other = (Rua) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }
}
