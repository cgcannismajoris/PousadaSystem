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
import java.util.ArrayList;
import modelo.Administrador;
import modelo.Cliente;
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
        String sql = "SELECT * FROM pessoa WHERE (login = ? and senha = ?)";
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
            
            rs.close();
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
        String sql = "SELECT * FROM pessoa INNER JOIN administrador "
                + "ON (pessoa.idPessoa = administrador.Pessoa_idPessoa)";

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
            
            rs.close();
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
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String sql
                = "INSERT INTO pessoa (nome, dataNascimento, login, senha, "
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
                    p.setId(rs.getInt("idPessoa"));

                    // Efetuar inserção na tabela Administrador
                    sql = "INSERT INTO administrador (Pessoa_idPessoa) VALUES (?);";
                    
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, p.getId());
                    stmt.executeUpdate();
                }
                
                rs.close();
            }
            
            // Inserir idPessoa na Tabela Cliente
            else if (p.getPrivilegio() == PessoaFactory.CLIENTE){
                // Executar uma pequena consulta
                sql = "SELECT idPessoa FROM pessoa "
                        + "WHERE ( privilegio = ? and nome = ? and login = ? "
                        + "and endereco = ? )";

                stmt = con.prepareStatement(sql);
                stmt.setInt(1, p.getPrivilegio());
                stmt.setString(2, p.getNome());
                stmt.setString(3, p.getLogin());
                stmt.setString(4, p.getEndereco());
                ResultSet rs = stmt.executeQuery();

                if (rs.next())
                {
                    p.setId(rs.getInt("idPessoa"));

                    // Efetuar inserção na tabela Administrador
                    sql = "INSERT INTO cliente (Pessoa_idPessoa) VALUES (?);";
                    
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, p.getId());
                    stmt.executeUpdate();
                }
                
                rs.close();
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
    
    // Método para consultar clientes
    @SuppressWarnings("null")
    public static ArrayList<Cliente> obterClientes()
    {
        Cliente clienteLido = null;
        ArrayList<Cliente> result = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String sql = "SELECT * FROM pessoa INNER JOIN cliente "
                + "ON (pessoa.idPessoa = cliente.Pessoa_idPessoa)";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                // Preencher os dados dos clientes
                clienteLido = (Cliente) PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.CLIENTE);
 
                clienteLido.setId(new Integer(rs.getString("idPessoa")));
                clienteLido.setLogin(rs.getString("login"));
                //clienteLido.setSenha(rs.getString("senha"));
                clienteLido.setNome(rs.getString("nome"));
                clienteLido.setEndereco(rs.getString("endereco"));
                clienteLido.setDataNascimento(rs.getDate("dataNascimento"));
                
                result.add(clienteLido);
            }
            
            rs.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
            clienteLido = null;
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

        return result;
    }
    
    // Método para consultar clientes
    @SuppressWarnings("null")
    public static Cliente obterClienteByName(String nome)
    {
        Cliente clienteLido = null;
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String sql = "SELECT * FROM pessoa INNER JOIN cliente "
                + "ON (pessoa.idPessoa = cliente.Pessoa_idPessoa) "
                + "WHERE ( pessoa.nome = ? )";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                // Preencher os dados dos clientes
                clienteLido = (Cliente) PessoaFactory.getPessoa(PessoaFactory.TipoPessoa.CLIENTE);
 
                clienteLido.setId(new Integer(rs.getString("idPessoa")));
                clienteLido.setLogin(rs.getString("login"));
                //clienteLido.setSenha(rs.getString("senha"));
                clienteLido.setNome(rs.getString("nome"));
                clienteLido.setEndereco(rs.getString("endereco"));
                clienteLido.setDataNascimento(rs.getDate("dataNascimento"));
            }
            
            rs.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            clienteLido = null;
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

        return clienteLido;
    }
}
