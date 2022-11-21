package br.com.shoppinglist.models;

public class Carrinho{
    
    private int id;
    private double total;
    
    public Carrinho(int id, double total){
        this.id = id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
