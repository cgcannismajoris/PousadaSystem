package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Hospedagem
{
    public static final int FIMHOSP_SUCESSO = 0;
    public static final int FIMHOSP_DATA_INVALIDA = 1;
    public static final int FINALIZADA = 1;
    public static final int EM_PROGRESSO = 0;

    private int id;
    private int quantAcomp;
    private Date dataInicio;
    private Date dataSaida;
    private int previsao;
    private Cliente cliente;
    
    private Chale chale;
    private ArrayList<ReciboDeServico> recibosDeServico;
    private Pagamento pagamento;
    private int flag = Hospedagem.EM_PROGRESSO;

    public Hospedagem(int quantAcomp, Date dataInicio, int previsao, Cliente cliente, Chale chale)
    {
        this.quantAcomp = quantAcomp;
        this.dataInicio = dataInicio;
        this.previsao = previsao;
        this.cliente = cliente;
        this.chale = chale;
    }

    public int finalizarHospedagem(Date dataSaida)
    {
        if (this.dataInicio.after(dataInicio))
        {
            return (FIMHOSP_DATA_INVALIDA);
        }

        return (Hospedagem.FIMHOSP_SUCESSO);
    }

    public BigDecimal calcularCustos()
    {
        BigDecimal custos = new BigDecimal(0.0);

        for (ReciboDeServico i : this.recibosDeServico)
        {
            custos.add(new BigDecimal(i.getValorTotal().toString()));
        }

        //adicionar código para aplicar multa
        return custos;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getQuantAcomp()
    {
        return quantAcomp;
    }

    public void setQuantAcomp(int quantAcomp)
    {
        this.quantAcomp = quantAcomp;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public int getPrevisao()
    {
        return previsao;
    }

    public void setPrevisao(int previsao)
    {
        this.previsao = previsao;
    }

    public Date getDataSaida()
    {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida)
    {
        this.dataSaida = dataSaida;
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

    public Pagamento getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento)
    {
        this.pagamento = pagamento;
    }
    
    
}
