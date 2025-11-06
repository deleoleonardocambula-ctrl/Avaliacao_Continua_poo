/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package veiculo;

/**
 *
 * @author Déleo Cambula
 */
// Veiculo.java
import java.io.Serializable;

public abstract class Veiculo implements Serializable {
    private String marca;
    private String modelo;
    private int ano;

    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Método abstrato
    public abstract String mover();

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getInfo() {
        return marca + " " + modelo + " (" + ano + ")";
    }

    @Override
    public String toString() {
        return getInfo() + " - " + mover();
    }
}