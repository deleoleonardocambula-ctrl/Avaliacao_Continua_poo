/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veiculo;

/**
 *
 * @author Déleo Cambula
 */
// Bicicleta.java
public class Bicicleta extends Veiculo {
    private int numeroMarchas;

    public Bicicleta(String marca, String modelo, int ano, int numeroMarchas) {
        super(marca, modelo, ano);
        this.numeroMarchas = numeroMarchas;
    }

    @Override
    public String mover() {
        return "Bicicleta pedalando com " + numeroMarchas + " marchas - Pedalando suavemente!";
    }

    public int getNumeroMarchas() {
        return numeroMarchas;
    }

    @Override
    public String toString() {
        return "Bicicleta: " + getInfo() + " - Marchas: " + numeroMarchas;
    }
}