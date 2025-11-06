/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicios5;

/**
 *
 * @author Déleo Cambula
 */
// Funcionario.java
public class Funcionario implements Pagavel {
    private String nome;
    private double salarioFixo;

    public Funcionario(String nome, double salarioFixo) {
        this.nome = nome;
        this.salarioFixo = salarioFixo;
    }

    @Override
    public double calcularPagamento() {
        return salarioFixo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return "Funcionário";
    }

    public double getSalarioFixo() {
        return salarioFixo;
    }

    @Override
    public String toString() {
        return "Funcionario{nome='" + nome + "', salarioFixo=" + salarioFixo + '}';
    }
}
