/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Déleo Cambula
 */
// Aluno.java
public abstract class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    private double mensalidade;
    private double notaFinal;

    public Aluno(String nome, int idade, String email, String telefone, 
                 String matricula, String curso, double mensalidade) {
        super(nome, idade, email, telefone);
        this.matricula = matricula;
        this.curso = curso;
        this.mensalidade = mensalidade;
        this.notaFinal = 0.0;
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public String getCurso() { return curso; }
    public double getMensalidade() { return mensalidade; }
    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }

    // Métodos abstratos
    public abstract boolean isDispensado();
    public abstract double calcularPagamento();

    // Método concreto para dispensa acadêmica
    public boolean isDispensadoAcademicamente() {
        return notaFinal >= 14;
    }

    @Override
    public String getDetalhes() {
        return String.format("Matrícula: %s, Curso: %s, Nota: %.1f, Mensalidade: %.2f, " +
                           "Dispensa: %s, Pagamento: %.2f", 
                           matricula, curso, notaFinal, mensalidade, 
                           isDispensado() ? "Sim" : "Não", calcularPagamento());
    }
}