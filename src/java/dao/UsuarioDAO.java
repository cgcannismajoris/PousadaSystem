package dao;

import factories.PessoaFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pessoa;
import controle.RequestVisitante;
import tools.GerenciadorDB;

// ESSA CLASSE PODE SER UM FACTORY METHOD
public class UsuarioDAO
{
    public static final String SUCESSO_LOGIN = "sucesso_login";
    public static final String FALHA_LOGIN   = "falha_login";
    
    private static Connection con = null;

    @SuppressWarnings("null")
    public static Pessoa autenticarUsuario(RequestVisitante visitante)
    {
        Pessoa pessoaLida = null;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql = "SELECT * FROM Pessoa WHERE (login = ? and senha = ?)";
        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, visitante.getLogin());
            stmt.setString(2, visitante.getSenha());

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                // Verificar o tipo de usuário (privilégio) autenticado e criar
                // objeto apropriado.
                switch (rs.getInt("privilegio"))
                {
                    case PessoaFactory.PROPRIETARIO:
                    {
                        pessoaLida = PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.PROPRIETARIO);
                    } break;
                    
                    case PessoaFactory.ADMINISTRADOR:
                    {
                        pessoaLida = PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.ADMINISTRADOR);
                    } break;
                    
                    case PessoaFactory.CLIENTE:
                    {
                        pessoaLida = PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.CLIENTE);
                    } break;
                }
                
                // Preencher os dados de Pessoa
                if (pessoaLida != null)
                {
                    // Por questões de segurança, otimi armazenamento de senha
                    pessoaLida.setId(new Integer(rs.getString("idPessoa")));
                    pessoaLida.setLogin(visitante.getLogin());
                    pessoaLida.setNome(rs.getString("nome"));
                    pessoaLida.setEndereco(rs.getString("endereco"));
                    pessoaLida.setDataNascimento(rs.getDate("dataNascimento"));
                }

            }

        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            pessoaLida = null;
        } finally
        {
            try
            {
                stmt.close();
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pessoaLida;
    }
}
