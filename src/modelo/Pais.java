package modelo;

import java.util.ArrayList;

public class Pais {
 
    public static final int JAEXISTE = 0;
    public static final int SUCESSO = 1;
    
    private Integer id;
    private String nome;
    private String DDI;
    private ArrayList<Estado> estados = new ArrayList<Estado>();

    public Pais(String nome, String DDI){
        this.nome = nome;
        this.DDI = DDI;
    }
    
    public Pais(String nome, String DDI, Estado estado){
        this.nome = nome;
        this.DDI = DDI;
        this.estados.add(estado);
    }

    public Pais(String nome, String DDI, ArrayList<Estado> estados){
        this.nome = nome;
        this.DDI = DDI;
        this.estados.addAll(estados);
    }
    
    public Estado pesquisarEstado(Estado estado){
        for(Estado e : this.estados){
            if(e.equals(estado)){
                return (e);
            }
        }
        
        return (null);
    }

    public int addEstado(Estado estado){
        if(this.estados.contains(estado)){
            return (Pais.JAEXISTE);
        }
        
        this.estados.add(estado);
        
        return (Pais.SUCESSO);
    }
    
    public int addEstados(ArrayList<Estado> estados){
        
        int counter = 0;
        
        for(Estado c : estados){
            if(!this.estados.contains(c)){
                this.estados.add(c);
                counter++;
            }
        }
        
        return (counter);
    }
    
    public void removeEstado(Estado estado){
        this.estados.remove(estado);
    }
    
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDDI(){
        return DDI;
    }

    public void setDDI(String DDI){
        this.DDI = DDI;
    }

    public ArrayList<Estado> getEstados(){
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados){
        this.estados = estados;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
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
        final Pais other = (Pais) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
 
