package factories;

import modelo.Administrador;
import modelo.Cliente;
import modelo.Pessoa;
import modelo.Proprietario;

// Fabric Method de Pessoa
public class PessoaFactory
{
    public enum TipoPessoa { PROPRIETARIO, ADMINISTRADOR, CLIENTE };
    
    public static final int PROPRIETARIO  = 0;
    public static final int ADMINISTRADOR = 1;
    public static final int CLIENTE       = 2;
    
    public static Pessoa getPessoa(TipoPessoa tipo)
    {
        switch(tipo)
        {
            case PROPRIETARIO : return (new Proprietario());
            case ADMINISTRADOR: return (new Administrador());
            case CLIENTE      : return (new Cliente());
        }
        
        return null;
    }
}
