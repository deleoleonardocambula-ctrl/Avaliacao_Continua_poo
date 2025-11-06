/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicios5;

/**
 *
 * @author Déleo Cambula
 */
public class Freelancer implements Pagavel {
    private String nome;
    private double valorHora;
    private int horasTrabalhadas;

    public Freelancer(String nome, double valorHora, int horasTrabalhadas) {
        this.nome = nome;
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calcularPagamento() {
        return valorHora * horasTrabalhadas;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return "Freelancer";
    }

    public double getValorHora() {
        return valorHora;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    @Override
    public String toString() {
        return "Freelancer{nome='" + nome + "', valorHora=" + valorHora + 
               ", horasTrabalhadas=" + horasTrabalhadas + '}';
    }
}