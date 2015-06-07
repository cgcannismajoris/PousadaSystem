package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Classe Singleton
public class GerenciadorDB
{
    private static GerenciadorDB instance;
    
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url    = 
            "jdbc:mysql://localhost:3306/db_pousada_system?zeroDateTimeBehavior=convertToNull";
    
    private static final String user = "javauser";
    private static final String password = "123";
    
    
    // Tags para navegação das páginas
    //public static final String BUSCA_FALHA = "failure";
    //public static final String BUSCA_SUCESSO = "success";
    
    public static GerenciadorDB getInstance()
    {
        if (instance == null)
            instance = new GerenciadorDB();
        
        return instance;
    }
    
    public Connection abrirConexao()
    {
        try
        {
            Class.forName(driver);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(GerenciadorDB.class.getName()).log(
                Level.SEVERE, null, ex);
        }
        
        Connection con = null;
        
        try
        {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex)
        {
            Logger.getLogger(GerenciadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
}
