package br.com.shoppinglist.view;

import br.com.shoppinglist.conexao.Conexao;

public class Principal {
    
    public static void main(String[] args) {

        Conexao con = new Conexao();
        con.getConexao();

        System.out.println("O status da conexão é: " + con.getStatusConexao());

    }
}
