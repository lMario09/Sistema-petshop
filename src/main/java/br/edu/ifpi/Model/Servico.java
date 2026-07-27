package br.edu.ifpi.Model;

public abstract class Servico {
    private String nome;

    public Servico(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract double getPreco();

    public abstract void executarServiço(Animal animal, Servico servico);
}
