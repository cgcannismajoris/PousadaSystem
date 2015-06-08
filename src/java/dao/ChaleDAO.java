/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Chale;
import modelo.Equipamento;
import tools.GerenciadorDB;

/**
 *
 * @author Gustavo Freitas
 */
public class ChaleDAO {
    
    private static Connection con = null;
    
    @SuppressWarnings("null")
    public static ArrayList<Chale> obterTodos(){
        
        ResultSet rs = null;
        
        Chale tmp;
        ArrayList<Chale> result = new ArrayList<>();
        ArrayList<Equipamento> equips = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String query = "SELECT * FROM chale";
        PreparedStatement stmt = null;
        
        //Obtém a lista de todos os chalés
        try {
            
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                tmp = new Chale(rs.getInt("idChale"), rs.getInt("numero"), new BigDecimal(rs.getDouble("diaria")));
                tmp.addEquipamento(EquipamentoDAO.obterTodosByChale(tmp.getId()));
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
    
    public static Chale obterByNumero(int numero){

        ResultSet rs = null;
        
        Chale tmp = null;
        ArrayList<Chale> result = new ArrayList<>();
        
        con = GerenciadorDB.getInstance().abrirConexao();
        String query = "SELECT * FROM Chale WHERE ( numero = ? )";
        PreparedStatement stmt = null;
        
        //Obtém a lista de todos os chalés
        try {
            
            stmt = con.prepareStatement(query);
            stmt.setInt(1, numero);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                tmp = new Chale(rs.getInt("idChale"), rs.getInt("numero"), new BigDecimal(rs.getDouble("diaria")));
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
        //Obtém os equipamentos do chalé
        if(tmp != null){
            tmp.addEquipamento(EquipamentoDAO.obterTodosByChale(tmp.getId()));
        }
        
        return (tmp);
    }
    
    @SuppressWarnings("null")
    public static boolean inserirChaleDAO(Chale c){
        boolean retorno = true;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql
                = "INSERT INTO Chale (numero, diaria) VALUES (?, ?);";

        PreparedStatement stmt = null;

        try
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, c.getNumero());
            stmt.setDouble(2, c.getDiaria().doubleValue());
            
            //Insere no banco
            stmt.executeUpdate();
            stmt.close();
            
            //Obtém o id do equipamento inserido
            sql = "SELECT idChale FROM chale "
                    + "WHERE ( numero = ? and diaria = ?)";
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getNumero());
            stmt.setDouble(2, c.getDiaria().doubleValue());
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                c.setId(rs.getInt("idChale"));
            }
            
            rs.close();
            
            //Insere as referências para os equipamentos
            sql = "INSERT INTO Chaleequipamento ( Chale_idChale , Equipamento_idEquipamento ) "
                    + "VALUES ( ? , ? )";
            
            for(Equipamento e : c.getEquipamentos()){
                //Fecho o statement antes para, em caso de exceção
                //não dar treta no finaly
                stmt.close();
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, c.getId());
                stmt.setInt(2, e.getId());
                stmt.executeUpdate();
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
