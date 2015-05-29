package modelo;

import java.util.ArrayList;

public class Bairro
{
    public static final int JAEXISTE = 0;
    public static final int SUCESSO = 1;

    private Integer id;
    private String nome;
    private Cidade cidade;
    private ArrayList<Rua> ruas = new ArrayList<>();
    

    public Bairro(Integer id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public Bairro(Integer id, String nome, Rua rua)
    {
        this.id = id;
        this.nome = nome;
        this.ruas.add(rua);
    }

    public Bairro(Integer id, String nome, ArrayList<Rua> ruas)
    {
        this.id = id;
        this.nome = nome;
        this.ruas.addAll(ruas);
    }

    public Rua pesquisarRua(Rua rua)
    {

        for (Rua r : this.ruas)
        {
            if (r.equals(rua))
            {
                return (r);
            }
        }

        return (null);
    }

    public int addRua(Rua rua)
    {

        if (this.ruas.contains(rua))
        {
            return (Bairro.JAEXISTE);
        }

        this.ruas.add(rua);

        return (Bairro.SUCESSO);
    }

    public int addRuas(ArrayList<Rua> ruas)
    {

        int counter = 0;

        for (Rua r : ruas)
        {
            if (!this.ruas.contains(r))
            {
                this.ruas.add(r);
                counter++;
            }
        }

        return (counter);
    }

    public void removerRua(Rua rua)
    {
        this.ruas.remove(rua);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public Cidade getCidade()
    {
        return cidade;
    }

    public void setCidade(Cidade cidade)
    {
        this.cidade = cidade;
    }

    public ArrayList<Rua> getRua()
    {
        return ruas;
    }

    public void setRua(ArrayList<Rua> ruas)
    {
        this.ruas = ruas;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Bairro other = (Bairro) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
}
