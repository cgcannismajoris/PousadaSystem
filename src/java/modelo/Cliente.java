package modelo;

public class Cliente extends Pessoa
{
    public Cliente() 
    {
        this.privilegio = 2;
    }
    
    @Override
    public String toString(){
        return ("Nome: " + this.nome + " - Login: " + this.login);
    }
}
