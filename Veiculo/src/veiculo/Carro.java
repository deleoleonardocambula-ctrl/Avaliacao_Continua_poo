/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veiculo;

/**
 *
 * @author Déleo Cambula
 */
// Carro.java
public class Carro extends Veiculo {
    private String tipoCombustivel;

    public Carro(String marca, String modelo, int ano, String tipoCombustivel) {
        super(marca, modelo, ano);
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public String mover() {
        return "Carro movendo-se com motor a " + tipoCombustivel + " - Vrum Vrum!";
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    @Override
    public String toString() {
        return "Carro: " + getInfo() + " - Combustível: " + tipoCombustivel;
    }
}