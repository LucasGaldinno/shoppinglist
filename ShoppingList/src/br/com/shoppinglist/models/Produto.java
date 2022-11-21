package br.com.shoppinglist.models;

public class Produto {
    
    private int id;
    private String nome;
    private int qtd;
    private Carrinho idcar;
    private double valor;
    
    public Produto(int id, String nome, int qtd, Carrinho idcar, double valor){
       this.id = id;
       this.nome = nome;
       this.qtd = qtd;
       this.valor = valor;
       this.idcar = idcar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Carrinho getIdcar() {
        return idcar;
    }

    public void setIdcar(Carrinho idcar) {
        this.idcar = idcar;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
