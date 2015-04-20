package modelo;

import java.util.ArrayList;

public class Estado {
 
    public static final int JAEXISTE = 0;
    public static final int SUCESSO = 1;
    
    private Integer id;
    private String nome;
    private String DDD;
    private Pais pais;
    
    private ArrayList<Cidade> cidades = new ArrayList<Cidade>();
    private ArrayList<Area> areas = new ArrayList<Area>();
    
    public Estado(String nome, Integer id, Pais pais){
        this.nome = nome;
        this.id = id;
        this.pais = pais;
    }
    
    public Estado(String nome, Integer id, Pais pais, Cidade cidade){
        this.nome = nome;
        this.id = id;
        this.pais = pais;
        this.cidades.add(cidade);
    }

    public Estado(String nome, Integer id, Pais pais, ArrayList<Cidade> cidades){
        this.nome = nome;
        this.id = id;
        this.pais = pais;
        this.cidades.addAll(cidades);
    }
    
    public Area pesquisarArea(Area area){
        for(Area a : this.areas){
            if(a.equals(area)){
                return (a);
            }
        }
        
        return (null);
    }
    
    public int addArea(Area area){
        
        if(this.areas.contains(area)){
            return (Estado.JAEXISTE);
        }
        
        this.areas.add(area);
        
        return (Estado.SUCESSO);
    }

    public int addAreas(ArrayList<Area> areas){

        int counter = 0;
        
        for(Area a : this.areas){
            if(!this.areas.contains(a)){
                this.areas.add(a);
                counter++;
            }
        }
        
        return (counter);
    }
    
    public void removerArea(Area area) {
        this.areas.remove(area);
    }

    public Cidade pesquisarCidade(Cidade cidade){
        for(Cidade c : this.cidades){
            if(c.equals(cidade)){
                return (c);
            }
        }
        
        return (null);
    }
    
    public int addCidade(Cidade cidade){
        
        if(this.cidades.contains(cidade)){
            return (Estado.JAEXISTE);
        }
        
        this.cidades.add(cidade);
        
        return (Estado.SUCESSO);
    }
    
    public int addCidades(ArrayList<Cidade> cidades){

        int counter = 0;
        
        for(Cidade c : cidades){
            if(!this.cidades.contains(c)){
                this.cidades.add(c);
                counter++;
            }
        }
        
        return (counter);
    }
    
    public void removerCidade(Cidade cidade){
        this.cidades.remove(cidade);
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

    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> cidades) {
        this.cidades = cidades;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }
    
}
 
