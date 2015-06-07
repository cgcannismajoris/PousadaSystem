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
import modelo.Administrador;
import tools.GerenciadorDB;

// ESSA CLASSE PODE SER UM FACTORY METHOD
public class UsuarioDAO
{

    public static final String SUCESSO_LOGIN = "sucesso_login";
    public static final String FALHA_LOGIN = "falha_login";

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
                    }
                    break;

                    case PessoaFactory.ADMINISTRADOR:
                    {
                        pessoaLida = PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.ADMINISTRADOR);
                    }
                    break;

                    case PessoaFactory.CLIENTE:
                    {
                        pessoaLida = PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.CLIENTE);
                    }
                    break;
                }

                // Preencher os dados de Pessoa
                if (pessoaLida != null)
                {
                    // Por questões de segurança, omiti armazenamento de senha
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

    // Método para consultar um administrador
    @SuppressWarnings("null")
    public static Administrador obterAdmin()
    {
        Administrador adminLido = null;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql = "SELECT * FROM Pessoa INNER JOIN Administrador "
                + "ON (Pessoa.idPessoa = Administrador.Pessoa_idPessoa)";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                // Preencher os dados de Administrador
                adminLido = new Administrador();

                adminLido.setId(new Integer(rs.getString("idPessoa")));
                adminLido.setLogin(rs.getString("login"));
                adminLido.setSenha(rs.getString("senha"));
                adminLido.setNome(rs.getString("nome"));
                adminLido.setEndereco(rs.getString("endereco"));
                adminLido.setDataNascimento(rs.getDate("dataNascimento"));
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

            adminLido = null;
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

        return adminLido;
    }

    @SuppressWarnings("null")
    public static boolean inserirPessoaDAO(Pessoa p)
    {
        boolean retorno = true;
        Integer idPessoa;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql
                = "INSERT INTO Pessoa (nome, dataNascimento, login, senha, "
                + "privilegio, endereco) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setDate(2, new java.sql.Date(p.getDataNascimento().getTime()));
            stmt.setString(3, p.getLogin());
            stmt.setString(4, p.getSenha());
            stmt.setInt(5, p.getPrivilegio());
            stmt.setString(6, p.getEndereco());
            stmt.executeUpdate();

            // Inserir idPessoa na Tabela Administrador
            if (p.getPrivilegio() == PessoaFactory.ADMINISTRADOR)
            {
                // Executar uma pequena consulta
                sql = "SELECT idPessoa FROM Pessoa "
                        + "WHERE (privilegio = " + p.getPrivilegio() + ")";

                stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                if (rs.next())
                {
                    idPessoa = rs.getInt("idPessoa");

                    // Efetuar inserção na tabela Administrador
                    sql = "INSERT INTO Administrador (Pessoa_idPessoa) VALUES (?);";
                    
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, idPessoa);
                    stmt.executeUpdate();
                }
            }

            retorno = true;

        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
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

        return retorno;
    }
}
