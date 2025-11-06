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

class Animal implements Serializable {
    private String nome;
    private String tipo;

    public Animal(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Animal: " + nome + " (" + tipo + ")";
    }
}