/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Déleo Cambula
 */
// AlunoBolseiro.java
public class AlunoBolseiro extends Aluno {

    public AlunoBolseiro(String nome, int idade, String email, String telefone, 
                        String matricula, String curso, double mensalidade) {
        super(nome, idade, email, telefone, matricula, curso, mensalidade);
    }

    @Override
    public boolean isDispensado() {
        return getNotaFinal() >= 16;
    }

    @Override
    public double calcularPagamento() {
        if (getNotaFinal() >= 16) {
            return 0.0; // Dispensa total
        } else {
            return getMensalidade() / 2; // Paga metade
        }
    }

    @Override
    public String getTipo() {
        return "Aluno Bolseiro";
    }

    @Override
    public String toString() {
        return super.toString() + ", Bolseiro";
    }
}