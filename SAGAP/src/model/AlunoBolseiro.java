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

// Aluno Bolseiro
public class AlunoBolseiro extends Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AlunoBolseiro(String nome, int idade, String matricula, String curso, double notaFinal) {
        super(nome, idade, matricula, curso, notaFinal);
    }

    @Override
    public boolean temDispensaAcademica() {
        return notaFinal >= 16; // dispensa com nota >= 16
    }

    @Override
    public boolean devePagarMensalidade() {
        // Só paga se não for dispensado
        return !temDispensaAcademica();
    }
    
    @Override
    public boolean isBolseiro() {
        return true;
    }
}
