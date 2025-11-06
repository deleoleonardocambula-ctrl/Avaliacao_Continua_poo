/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pessoasealunos;

/**
 *
 * @author Déleo Cambula
 */
// Pessoa.java
import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    // Método toString
    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', idade=" + idade + "}";
    }

    public String getTipo() {
        return "Pessoa";
    }

    public String getDetalhes() {
        return "Idade: " + idade;
    }
}