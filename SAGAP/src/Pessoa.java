/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.Serializable;

/**
 *
 * @author Déleo Cambula
 */
// Pessoa.java
import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private String nome;
    private int idade;
    private String email;
    private String telefone;

    public Pessoa(String nome, int idade, String email, String telefone) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // Métodos abstratos
    public abstract String getTipo();
    public abstract String getDetalhes();

    @Override
    public String toString() {
        return getTipo() + "{nome='" + nome + "', idade=" + idade + 
               ", email='" + email + "', telefone='" + telefone + "'}";
    }
}