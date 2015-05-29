package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;


public class Chale
{
    public static final int SUCESSO_ADDEQUIP = 0;
    public static final int FALHA_JAPOSSUI = 1;
    
    private Integer id;
    private Integer numero;
    private BigDecimal diaria;

    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Equipamento> equipamentos = new ArrayList<>();

    
    public Chale(Integer id, Integer numero, BigDecimal diaria)
    {
        this.id = id;
        this.numero = numero;
        this.diaria = diaria;
    }

    public Chale(Integer id, Integer numero, BigDecimal diaria,
            ArrayList<Equipamento> equipamento)
    {
        this.id = id;
        this.numero = numero;
        this.diaria = diaria;
        this.equipamentos = equipamento;
    }

    public int addEquipamento(Equipamento equip)
    {
        if (this.equipamentos.contains(equip))
            return FALHA_JAPOSSUI;

        this.equipamentos.add(equip);

        return SUCESSO_ADDEQUIP;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getNumero()
    {
        return numero;
    }

    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public BigDecimal getDiaria()
    {
        return diaria;
    }

    public void setDiaria(BigDecimal diaria)
    {
        this.diaria = diaria;
    }

    public ArrayList<Reserva> getReservas()
    {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas)
    {
        this.reservas = reservas;
    }

    public ArrayList<Equipamento> getEquipamentos()
    {
        return equipamentos;
    }

    public void setEquipamentos(ArrayList<Equipamento> equipamentos)
    {
        this.equipamentos = equipamentos;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.numero);
        hash = 67 * hash + Objects.hashCode(this.diaria);
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
        final Chale other = (Chale) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero))
        {
            return false;
        }
        if (!Objects.equals(this.diaria, other.diaria))
        {
            return false;
        }
        return true;
    }
}
