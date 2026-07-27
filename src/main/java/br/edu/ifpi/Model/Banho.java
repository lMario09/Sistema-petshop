package br.edu.ifpi.Model;

public class Banho extends Servico {
    private double preco;

    public Banho(String nome) {
        super(nome);
        this.preco = 50.0;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void executarServiço(Animal animal, Servico servico) {
        System.out.println("Dando banho em " + animal.getNome());
    }
}
