package modelo;

import java.util.Date;

public class Hospedagem {
 
	private int id;
	 
	private int quantAcomp;
	 
	private Date dataInicio;
	 
	private int previsao;
	 
	private Date dataSaida;
	 
	private Cliente cliente;
	 
	private Servico servico;
	 
	private ReciboDeServico[] reciboDeServico;
	 
	private Chale chale;
	 
	private Pagamento pagamento;
	 
	public Hospedagem(int quantAcomp, Date dataInicio, int previsao) {
	 
	}
	 
	public void finalizarHospedagem() {
	 
	}
	 
	public void realizarPagamento() {
	 
	}
	 
	public int getQuantAcomp() {
		return 0;
	}
	 
	public void setQuantAcomp(int quantAcomp) {
	 
	}
	 
	public Date getDataInicio() {
		return null;
	}
	 
	public void setDataInicio(Date dataInicio) {
	 
	}
	 
	public Date getDataSaida() {
		return null;
	}
	 
	public void setDataSaida(Date dataSaida) {
	 
	}
	 
	public int getPrevisao() {
		return 0;
	}
	 
	public void setPrevisao(int previsao) {
	 
	}
	 
}
 
