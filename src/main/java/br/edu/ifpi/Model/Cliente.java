package br.edu.ifpi.Model;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Animal> animais = new ArrayList<>();

    public Endereco getEndereco() { return super.getEndereco(); }
    public void setEndereco(Endereco endereco) { super.setEndereco(endereco); }
    public List<Animal> getAnimais() { return animais; }
    public void adicionarAnimal(Animal animal) { this.animais.add(animal); }
}