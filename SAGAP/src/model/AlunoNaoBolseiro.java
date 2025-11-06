/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Déleo Cambula
 */

import java.io.Serializable;
 
public class AlunoNaoBolseiro extends Aluno implements Serializable{
    private static final long serialVersionUID = 1L;
    public AlunoNaoBolseiro(String nome, int idade, String matricula, String curso, double notaFinal) {
        super(nome, idade, matricula, curso, notaFinal);
    }

    @Override
    public boolean temDispensaAcademica() {
        return notaFinal >= 14; // dispensa com nota >= 14
    }

    @Override
    public boolean devePagarMensalidade() {
        return true; // sempre paga, mesmo dispensado
    }
    
    @Override
    public boolean isBolseiro() {
        return false;
    } 
}
