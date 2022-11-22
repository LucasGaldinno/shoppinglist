package br.com.shoppinglist.controller;

import br.com.shoppinglist.conexao.Conexao;
import br.com.shoppinglist.models.Carrinho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrinhoController {
    
    
    public void insereCarro(Carrinho car) {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {

            String sql = "";
            sql += "";
            sql += "INSERT INTO carrinho"
                    + "(idCar, total)"
                    + "VALUES"
                    + "(?, ?)";

            st = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setDouble(1, car.getTotal());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    car.setId(id);
                }
                rs.close();
            } else {
                throw new SQLException("Erro inesperado! Nenhuma linha afetada!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexao.fechaConexao();
        }

    }
    
    public void deletarPorId(Integer id) throws SQLException {

        Conexao conexao = new Conexao();
        PreparedStatement st = null;

        try {
            String sql = "";
            sql += "DELETE FROM carrinho"
                    + "WHERE idCar = ?";

            st = conexao.getConexao().prepareStatement(sql);

            st.setInt(1, id);
            st.executeUpdate(); 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            st.close();
        }
    }
}
