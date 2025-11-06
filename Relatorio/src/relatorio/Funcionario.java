/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

/**
 *
 * @author Déleo Cambula
 */
import java.io.Serializable;

class Funcionario implements Serializable {
    private String nome;
    private String cargo;

    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() { return nome; }
    public String getCargo() { return cargo; }

    @Override
    public String toString() {
        return "Funcionário: " + nome + " (" + cargo + ")";
    }
}