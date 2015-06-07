package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Estado
{
    public static final int JAEXISTE = 0;
    public static final int SUCESSO = 1;

    private Integer id;
    private String nome;
    private String DDD;
    private Pais pais;

    private ArrayList<Cidade> cidades = new ArrayList<>();
    private ArrayList<Area> areas = new ArrayList<>();

    public Estado(Integer id, String nome, Pais pais)
    {
        this.nome = nome;
        this.id = id;
        this.pais = pais;
    }

    public Estado(Integer id, String nome, Pais pais, Cidade cidade)
    {
        this.nome = nome;
        this.id = id;
        this.pais = pais;
        this.cidades.add(cidade);
    }

    public Estado(Integer id, String nome, Pais pais, ArrayList<Cidade> cidades)
    {
        this.nome = nome;
        this.id = id;
        this.pais = pais;
        this.cidades.addAll(cidades);
    }

    public Area pesquisarArea(Area area)
    {
        for (Area a : this.areas)
        {
            if (a.equals(area))
            {
                return (a);
            }
        }

        return (null);
    }

    public int addArea(Area area)
    {

        if (this.areas.contains(area))
        {
            return (Estado.JAEXISTE);
        }

        this.areas.add(area);

        return (Estado.SUCESSO);
    }

    public int addAreas(ArrayList<Area> areas)
    {

        int counter = 0;

        for (Area a : this.areas)
        {
            if (!this.areas.contains(a))
            {
                this.areas.add(a);
                counter++;
            }
        }

        return (counter);
    }

    public void removerArea(Area area)
    {
        this.areas.remove(area);
    }

    public Cidade pesquisarCidade(Cidade cidade)
    {
        for (Cidade c : this.cidades)
        {
            if (c.equals(cidade))
            {
                return (c);
            }
        }

        return (null);
    }

    public int addCidade(Cidade cidade)
    {

        if (this.cidades.contains(cidade))
        {
            return (Estado.JAEXISTE);
        }

        this.cidades.add(cidade);

        return (Estado.SUCESSO);
    }

    public int addCidades(ArrayList<Cidade> cidades)
    {

        int counter = 0;

        for (Cidade c : cidades)
        {
            if (!this.cidades.contains(c))
            {
                this.cidades.add(c);
                counter++;
            }
        }

        return (counter);
    }

    public void removerCidade(Cidade cidade)
    {
        this.cidades.remove(cidade);
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

    public String getDDD()
    {
        return DDD;
    }

    public void setDDD(String DDD)
    {
        this.DDD = DDD;
    }

    public Pais getPais()
    {
        return pais;
    }

    public void setPais(Pais pais)
    {
        this.pais = pais;
    }

    public ArrayList<Cidade> getCidades()
    {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> cidades)
    {
        this.cidades = cidades;
    }

    public ArrayList<Area> getAreas()
    {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas)
    {
        this.areas = areas;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.DDD);
        hash = 89 * hash + Objects.hashCode(this.pais);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome))
        {
            return false;
        }
        if (!Objects.equals(this.DDD, other.DDD))
        {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais))
        {
            return false;
        }
        return true;
    }
}
