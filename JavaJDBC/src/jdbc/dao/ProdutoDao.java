package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.conexao.ConnectionFactory;
import jdbc.modelo.Produto;

public class ProdutoDao {

    private Connection connection;

    public ProdutoDao() {
        connection = new ConnectionFactory().getConnection();
    
    }
    
    public ArrayList<Produto> consulta(
            float precoInferior, float precoSuperior){
        
        String sql = "SELECT * FROM new_table WHERE "
                + "preco >= " + precoInferior + " and " +
                "preco <= " + precoSuperior;
        
        PreparedStatement stmt;
        
        try {
            stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Produto> arlProduto = new ArrayList<Produto>();
            
            while(rs.next() != false){
             
                
                arlProduto.add(new Produto(
                   rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
                
            }
            return arlProduto;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }

    
    
    public ArrayList<Produto> consulta(){
        
        String sql = "SELECT * FROM new_table";
        
        PreparedStatement stmt;
        
        try {
            stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Produto> arlProduto = new ArrayList<Produto>();
            
            while(rs.next() != false){
             
                
                arlProduto.add(new Produto(
                   rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
                
            }
            return arlProduto;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }

    public boolean insere(Produto p) {
        String sql = "INSERT INTO new_table "
                + "(nome, preco, peso) VALUES (?, ?, ?)";

            PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
             //setando valores
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getPreco());
            stmt.setFloat(3, p.getPeso());

            //executando a insercao
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean remove(String nomeProduto) {
        String sql = "DELETE FROM new_table "
                + " WHERE nome LIKE " + nomeProduto;

            PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
             //executando a remoção
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}


