/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Equipamento;
import tools.GerenciadorDB;

/**
 *
 * @author Gustavo Freitas
 */
public class EquipamentoDAO {
    
    private static Connection con = null;
    
    @SuppressWarnings("null")
    public static ArrayList<Equipamento> obterTodos(){
        
        ResultSet rs = null;
        Equipamento tmp;
        ArrayList<Equipamento> result = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String query = "SELECT * FROM equipamento";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                tmp = new Equipamento(rs.getInt("idEquipamento"), rs.getString("descricao"));
                result.add(tmp);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
        }finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return (result);
    }
    
    @SuppressWarnings("null")
    public static ArrayList<Equipamento> obterTodosByDesc(String descricao){
        
        ResultSet rs = null;
        Equipamento tmp;
        ArrayList<Equipamento> result = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String query = "SELECT * FROM equipamento WHERE ( descricao = ? )";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, descricao);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                tmp = new Equipamento(rs.getInt("idEquipamento"), rs.getString("descricao"));
                result.add(tmp);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
        }finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return (result);
    }
    
    @SuppressWarnings("null")
    public static ArrayList<Equipamento> obterTodosByChale(Integer idChale){
        ResultSet rs = null;
        Equipamento tmp;
        ArrayList<Equipamento> result = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String sql = "SELECT Equipamento.idEquipamento, Equipamento.descricao "
                + "FROM Equipamento INNER JOIN Chaleequipamento "
                + "ON (Equipamento.idEquipamento = Chaleequipamento.Equipamento_idEquipamento) "
                + "WHERE (Chaleequipamento.Chale_idChale = ? )";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idChale);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                tmp = new Equipamento(rs.getInt("idEquipamento"), rs.getString("descricao"));
                result.add(tmp);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
        }finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return (result);
    }
    
    @SuppressWarnings("null")
    public static boolean inserirEquipamentoDAO(Equipamento e){

        boolean retorno = true;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql
                = "INSERT INTO equipamento (descricao) VALUES (?);";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, e.getDescricao());
            
            //Insere no banco
            stmt.executeUpdate();
            stmt.close();
            
            //Obtém o id do equipamento inserido
            sql = "SELECT idEquipamento FROM equipamento "
                    + "WHERE ( descricao = ? )";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, e.getDescricao());
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                e.setId(rs.getInt("idEquipamento"));
            }
            
            rs.close();
            retorno = true;
        } catch (SQLException ex)
        {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally
        {
            try
            {
                stmt.close();
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }
}
