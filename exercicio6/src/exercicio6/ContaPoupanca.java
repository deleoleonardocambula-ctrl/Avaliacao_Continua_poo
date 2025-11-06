/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio6;

/**
 *
 * @author Déleo Cambula
 */
public class ContaPoupanca implements Conta {
    private String numero;
    private double saldo;
    private static final double PERCENTUAL_TAXA = 0.02; // 2%

    public ContaPoupanca(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    @Override
    public double calcularTaxa() {
        return saldo * PERCENTUAL_TAXA;
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
        return "Poupança";
    }

    @Override
    public String toString() {
        return "ContaPoupanca{numero=" + numero + ", saldo=" + saldo + '}';
    }
}