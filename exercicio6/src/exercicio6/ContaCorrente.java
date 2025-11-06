/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio6;

/**
 *
 * @author Déleo Cambula
 */
// ContaCorrente.java
public class ContaCorrente implements Conta {
    private String numero;
    private double saldo;
    private static final double TAXA_FIXA = 10.0;

    public ContaCorrente(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    @Override
    public double calcularTaxa() {
        return TAXA_FIXA;
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public String getTipo() {
        return "Corrente";
    }

    @Override
    public String toString() {
        return "ContaCorrente{numero=" + numero + ", saldo=" + saldo + '}';
    }
}
