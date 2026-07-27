package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
public class Gato extends Animal {
    public void emitirSom() {
        System.out.println("Miau!");
    }
}
