package br.edu.ifpi.Model;

public class Tosa extends Servico {
    private double preco;

    public Tosa(String nome) {
        super(nome);
        this.preco = 75.0;
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
        System.out.println("Fazendo tosa em " + animal.getNome());
    }
}
