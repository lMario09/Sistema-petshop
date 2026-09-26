package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn (name = "endereco_id", unique = true)
    private Endereco endereco;

    public String getCpf() {
        return cpf; 
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; 
    }
    public String getNome() {
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }
    public String getTelefone() {
        return telefone; 
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone; 
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
