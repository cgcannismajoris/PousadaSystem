package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Cliente {
 
    public static final int SUCESSO = 0;
    public static final int JAPOSSUI = 1;
    
    private Integer id;
    private String nome;
    private Date dataNascimento;
    private Endereco endereco;

    private Reserva reserva;
    private ArrayList<Telefone> telefones;
    private ArrayList<Hospedagem> hospedagens;

    public int realizarReserva(Reserva reserva) {

        if(this.reserva != null){
            return (Cliente.JAPOSSUI);
        }
        
        this.reserva = reserva;
        
        return (Cliente.SUCESSO);
    }

    public int realizarHospedagem(Hospedagem hospedagem) {
        
        if(this.hospedagens.contains(hospedagem)){
            return (Cliente.JAPOSSUI);
        }
        
        this.hospedagens.add(hospedagem);
        
        return (Cliente.SUCESSO);
    }

    public Telefone pesquisarTelefone(Telefone telefone){
        
        for(Telefone t : this.telefones){
            if(t.equals(telefone)){
                return (t);
            }
        }
        
        return (null);
    }
    
    public int addTelefone(Telefone telefone) {
        if(this.telefones.contains(telefone)){
            return (Cliente.JAPOSSUI);
        }
        
        this.telefones.add(telefone);
        
        return (Cliente.SUCESSO);
    }

    public int addTelefones(ArrayList<Telefone> telefones){
        
        int counter = 0;
        
        for(Telefone t : this.telefones){
            if(!this.telefones.contains(t)){
                this.telefones.add(t);
                counter++;
            }
        }
        
        return (counter);
    }
    
    public void removerTelefone(Telefone telefone){
        this.telefones.remove(telefone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public ArrayList<Hospedagem> getHospedagens() {
        return hospedagens;
    }

    public void setHospedagens(ArrayList<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
 
