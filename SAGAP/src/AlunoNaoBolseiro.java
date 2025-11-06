/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Déleo Cambula
 */
public class AlunoNaoBolseiro extends Aluno {

    public AlunoNaoBolseiro(String nome, int idade, String email, String telefone, 
                           String matricula, String curso, double mensalidade) {
        super(nome, idade, email, telefone, matricula, curso, mensalidade);
    }

    @Override
    public boolean isDispensado() {
        return getNotaFinal() >= 14;
    }

    @Override
    public double calcularPagamento() {
        return getMensalidade(); // Sempre paga a mensalidade completa
    }

    @Override
    public String getTipo() {
        return "Aluno Não-Bolseiro";
    }

    @Override
    public String toString() {
        return super.toString() + ", Não-Bolseiro";
    }
}
