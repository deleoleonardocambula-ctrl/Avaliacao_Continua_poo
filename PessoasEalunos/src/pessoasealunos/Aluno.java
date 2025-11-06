/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pessoasealunos;

/**
 *
 * @author Déleo Cambula
 */
public class Aluno extends Pessoa {
    private String matricula;

    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }

    // Getter
    public String getMatricula() {
        return matricula;
    }

    // Sobrescrevendo toString
    @Override
    public String toString() {
        return "Aluno{nome='" + getNome() + "', idade=" + getIdade() + 
               ", matricula='" + matricula + "'}";
    }

    // Sobrescrevendo getTipo
    @Override
    public String getTipo() {
        return "Aluno";
    }

    // Sobrescrevendo getDetalhes
    @Override
    public String getDetalhes() {
        return "Idade: " + getIdade() + ", Matrícula: " + matricula;
    }
}