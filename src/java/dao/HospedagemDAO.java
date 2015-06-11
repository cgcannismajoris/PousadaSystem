package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Hospedagem;
import tools.GerenciadorDB;

public class HospedagemDAO
{
    private static Connection con = null;
    
    @SuppressWarnings("null")
    public static boolean realizarHospDAO(Hospedagem hosp)
    {
        boolean retorno = true;

        con = GerenciadorDB.getInstance().abrirConexao();
        String sql =
        
        "INSERT INTO hospedagem (quantAcomp, dataInicio, previsao, Cliente_idCliente, Chale_idChale)"
                + "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement stmt = null;
        
        try
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, hosp.getQuantAcomp());
            stmt.setDate(2, new java.sql.Date(hosp.getDataInicio().getTime()));
            stmt.setInt(3, hosp.getPrevisao());
            stmt.setInt(4, hosp.getCliente().getId());
            stmt.setInt(5, hosp.getChale().getId());
            
            //Insere no banco
            stmt.executeUpdate();
            stmt.close();
            
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
