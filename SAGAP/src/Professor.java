/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Déleo Cambula
 */
// Professor.java
public class Professor extends Pessoa {
    private String disciplina;
    private double salario;
    private String departamento;

    public Professor(String nome, int idade, String email, String telefone, 
                    String disciplina, double salario, String departamento) {
        super(nome, idade, email, telefone);
        this.disciplina = disciplina;
        this.salario = salario;
        this.departamento = departamento;
    }

    // Getters
    public String getDisciplina() { return disciplina; }
    public double getSalario() { return salario; }
    public String getDepartamento() { return departamento; }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public String getDetalhes() {
        return String.format("Disciplina: %s, Salário: %.2f, Departamento: %s", 
                           disciplina, salario, departamento);
    }

    @Override
    public String toString() {
        return super.toString() + ", disciplina='" + disciplina + 
               "', salario=" + salario + ", departamento='" + departamento + "'}";
    }
}