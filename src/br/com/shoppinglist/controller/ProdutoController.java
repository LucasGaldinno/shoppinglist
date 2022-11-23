package br.com.shoppinglist.controller;

import br.com.shoppinglist.conexao.Conexao;
import br.com.shoppinglist.model.Produto;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    // Metodo de cadastro de produto
    public void cadastraProduto(Produto produto) {

        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {

            String sql = "";
            sql += "";
            sql += "INSERT INTO produto"
                    + "(nome, qtd, valor)"
                    + "VALUES"
                    + "(?, ?, ?)";

            st = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, produto.getNome());
            st.setInt(2, produto.getQtd());
            st.setDouble(3, produto.getValor());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    produto.setId(id);
                }
                rs.close();
            } else {
                throw new SQLException("Erro inesperado! Nenhum produto cadastrado!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexao.fechaConexao();
        }
    }

    // Metodo de alterar produto
    public void AlterarProduto(Produto prod) throws SQLException {

        Conexao conexao = new Conexao();

        PreparedStatement st = null;
        String sql = "UPDATE produto SET nome=?, qtd=?, valor=? WHERE id=?";

        try {
            st = conexao.getConexao().prepareStatement(sql);

            st.setString(1, prod.getNome());
            st.setInt(2, prod.getQtd());
            st.setDouble(3, prod.getValor());
            st.setInt(4, prod.getId());

            st.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            st.close();
        }
    }

    // Metodo de excluir produto       
    public void ExcluirProduto(int id) throws SQLException {

        Conexao conexao = new Conexao();
        PreparedStatement st = null;

        String sql = "DELETE FROM produto WHERE id=?";

        try {
            st = conexao.getConexao().prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            st.close();
        }
    }

    // Metodo de busca de produto
    public List<Produto> buscaProduto(String nome) throws SQLException {

        Conexao con = new Conexao();
        con.getConexao();
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        List<Produto> listaProduto = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto WHERE nome LIKE ?;";

            stmt = con.getConexao().prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");

            resultado = stmt.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();

                prod.setNome(resultado.getString("nome"));
                prod.setQtd(resultado.getInt("qtd"));
                prod.setValor(resultado.getDouble("valor"));

                listaProduto.add(prod);
            }

            return listaProduto;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;

        } finally {
            resultado.close();
            stmt.close();
            con.getConexao().close();
        }
    }

    public Produto buscaPorId(int id) {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "";
            sql += "";
            sql += "SELECT * FROM produto "
                    + "WHERE id = ?";

            st = conexao.getConexao().prepareStatement(sql);

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {

                Produto prod = new Produto();

                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setQtd(rs.getInt("qtd"));
                prod.setValor(rs.getDouble("valor"));

                return prod;
            }
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexao.fechaConexao();
        }
        return null;
    }

    public List<Produto> obterListaDeProduto() throws SQLException {

        Conexao con = new Conexao();
        con.getConexao();
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            String sql = "SELECT * FROM produto";

            comando = con.getConexao().prepareStatement(sql);
            resultado = comando.executeQuery();

            List<Produto> listaProduto;
            listaProduto = new ArrayList<>();

            while (resultado.next()) {
                Produto prod = new Produto();

                prod.setNome(resultado.getString("nome"));
                prod.setQtd(resultado.getInt("qtd"));
                prod.setValor(resultado.getDouble("valor"));

                listaProduto.add(prod);
            }

            return listaProduto;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            resultado.close();
            comando.close();
            con.getConexao().close();
        }
    }
}
