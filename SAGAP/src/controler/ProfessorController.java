/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;


import model.Professor;
import java.util.ArrayList;

public class ProfessorController {
    
    // Cadastrar professor
    public boolean cadastrarProfessor(String nome, int idade, String disciplina, double salario) {
        if (validarDadosProfessor(nome, idade, disciplina, salario)) {
            Professor professor = new Professor(nome, idade, disciplina, salario);
            return Professor.gravarProfessor(professor);
        }
        return false;
    }
    
    // Listar todos os professores
    public ArrayList<Professor> listarProfessores() {
        return Professor.lerProfessor();
    }
    
    // Remover professor
    public boolean removerProfessor(String nome) {
        ArrayList<Professor> professores = listarProfessores();
        boolean removido = professores.removeIf(prof -> prof.getNome().equalsIgnoreCase(nome));
        
        if (removido) {
            return reescreverArquivo(professores);
        }
        return false;
    }
    
    // Calcular salário médio dos professores
    public double calcularSalarioMedio() {
        ArrayList<Professor> professores = listarProfessores();
        if (professores.isEmpty()) return 0;
        
        double total = 0;
        for (Professor professor : professores) {
            total += professor.getSalario();
        }
        return total / professores.size();
    }
    
    // Contar total de professores
    public int totalProfessores() {
        return listarProfessores().size();
    }
    
    // Método privado para reescrever o arquivo (para atualizações e remoções)
    private boolean reescreverArquivo(ArrayList<Professor> lista) {
        try {
            // Limpa o arquivo atual
            new java.io.FileOutputStream("lerProfessor.dat").close();
            
            // Reescreve com a lista atualizada
            for (Professor professor : lista) {
                Professor.gravarProfessor(professor);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao reescrever arquivo: " + e.getMessage());
            return false;
        }
    }
    
    // Validação de dados
    private boolean validarDadosProfessor(String nome, int idade, String disciplina, double salario) {
        return !nome.isEmpty() && 
               idade >= 23 && idade <= 70 && // idade válida para professor
               !disciplina.isEmpty() && 
               salario >= 0; // salário não pode ser negativo
    }
}