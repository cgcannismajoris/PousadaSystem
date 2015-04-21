package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Servico
{

    private Integer id;
    private String nome;
    private String descricao;

    private ArrayList<ReciboDeServico> reciboDeServico = new ArrayList<>();
    

    public Servico(Integer id, String nome, String descricao, Hospedagem hospedagem)
    {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public ArrayList<ReciboDeServico> getReciboDeServico()
    {
        return reciboDeServico;
    }

    public void setReciboDeServico(ArrayList<ReciboDeServico> reciboDeServico)
    {
        this.reciboDeServico = reciboDeServico;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.descricao);
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
        final Servico other = (Servico) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome))
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
