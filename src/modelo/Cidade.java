package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Cidade {
 
    public static final int JAEXISTE = 0;
    public static final int SUCESSO = 1;
    
    private Integer id;
    private String nome; 
    private Estado estado;

    private ArrayList<Bairro> bairros;

    public Cidade(String nome, Integer id, Estado estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade(String nome, Integer id, Estado estado, Bairro bairro){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.bairros.add(bairro);
    }

    public Cidade(String nome, Integer id, Estado estado, ArrayList<Bairro> bairros){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.bairros.addAll(bairros);
    }
    
    public Bairro pesquisarBairro(Bairro bairro){
        
        for(Bairro b : this.bairros){
            if(b.equals(bairro)){
                return (b);
            }
        }
        
        return (null);
    }
    
    public int addBairro(Bairro bairro){
       
        if(this.bairros.contains(bairro)){
           return (Cidade.JAEXISTE);
        }
        
        this.bairros.add(bairro);
            
        return (Cidade.SUCESSO);
    }
    
    public int addBairros(ArrayList<Bairro> bairros){
        
        int counter = 0;
        
        for(Bairro b : bairros){
            if(!this.bairros.contains(b)){
                this.bairros.add(b);
                counter++;
            }
        }
        
        return (counter);
    }
    
    public void removerBairro(Bairro bairro){
        this.bairros.remove(bairro);
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(ArrayList<Bairro> bairros) {
        this.bairros = bairros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
 
