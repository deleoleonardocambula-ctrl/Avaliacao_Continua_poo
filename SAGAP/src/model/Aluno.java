/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
// Classe Aluno (herda de Pessoa)
public abstract class Aluno extends Pessoa implements Serializable{
    protected String matricula;
    protected String curso;
    protected double notaFinal;

    public Aluno(String nome, int idade, String matricula, String curso, double notaFinal) {
        super(nome, idade);
        this.matricula = matricula;
        this.curso = curso;
        this.notaFinal = notaFinal;
    }

    public abstract boolean temDispensaAcademica();
    public abstract boolean devePagarMensalidade();
    public abstract boolean isBolseiro();

    public static Boolean gravarAluno(Aluno aluno) {
        ArrayList<Aluno> lista = lerAluno(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(aluno);

        try (FileOutputStream meuFicheiro = new FileOutputStream("Aluno.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Aluno> lerAluno() {
        File ficheiro = new File("Aluno.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Aluno>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    } 

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }
    
    
}
