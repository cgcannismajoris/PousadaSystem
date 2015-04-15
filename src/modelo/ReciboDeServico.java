package modelo;

import java.sql.Time;
import java.util.Date;

public class ReciboDeServico {
 
	private int id;
	 
	private Date data;
	 
	private Time hora;
	 
	private float valorTotal;
	 
	private Servico servico;
	 
	private Hospedagem hospedagem;
	 
	private Pagamento pagamento;
	 
	public ReciboDeServico(Servico servico, Date data, Time hora, float valorTotal) {
	 
	}
	 
	public Date getData() {
		return null;
	}
	 
	public void setData(Date data) {
	 
	}
	 
	public Time getHora() {
		return null;
	}
	 
	public void setHora(Time hora) {
	 
	}
	 
	public float getValorTotal() {
		return 0;
	}
	 
	public void setValorTotal(float valorTotal) {
	 
	}
	 
	public Servico getServico() {
		return null;
	}
	 
	public void setServico(Servico servico) {
	 
	}
	 
}
 
