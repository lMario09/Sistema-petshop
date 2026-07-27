package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
public class Cachorro extends Animal {
    public void emitirSom() {
        System.out.println("Au au!");
    }
}
