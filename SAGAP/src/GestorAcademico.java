/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Déleo Cambula
 */
// GestorAcademico.java
import java.io.*;
import java.util.ArrayList;

public class GestorAcademico {
    
    public static ArrayList<Pessoa> ler() {
        File ficheiro = new File("DadosAcademicos.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>();
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Pessoa>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static Boolean gravar(Pessoa pessoa) {
        ArrayList<Pessoa> lista = ler();

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(pessoa);

        try (FileOutputStream meuFicheiro = new FileOutputStream("DadosAcademicos.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }
    
    public static Boolean salvarLista(ArrayList<Pessoa> lista) {
        try (FileOutputStream meuFicheiro = new FileOutputStream("DadosAcademicos.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar lista: " + e.getMessage());
            return false;
        }
    }

    // Métodos recursivos para relatórios
    public static String gerarRelatorioAlunos(ArrayList<Pessoa> pessoas) {
        return gerarRelatorioAlunosRecursivo(pessoas, 0, new StringBuilder()).toString();
    }
    
    private static StringBuilder gerarRelatorioAlunosRecursivo(ArrayList<Pessoa> pessoas, 
                                                             int index, StringBuilder sb) {
        if (index >= pessoas.size()) {
            return sb;
        }
        
        Pessoa pessoa = pessoas.get(index);
        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;
            sb.append("=== ").append(aluno.getTipo()).append(" ===\n");
            sb.append("Nome: ").append(aluno.getNome()).append("\n");
            sb.append("Matrícula: ").append(aluno.getMatricula()).append("\n");
            sb.append("Curso: ").append(aluno.getCurso()).append("\n");
            sb.append("Nota Final: ").append(String.format("%.1f", aluno.getNotaFinal())).append("\n");
            sb.append("Dispensado: ").append(aluno.isDispensado() ? "Sim" : "Não").append("\n");
            sb.append("Pagamento: R$ ").append(String.format("%.2f", aluno.calcularPagamento())).append("\n");
            sb.append("\n");
        }
        
        return gerarRelatorioAlunosRecursivo(pessoas, index + 1, sb);
    }
    
    public static String gerarRelatorioProfessores(ArrayList<Pessoa> pessoas) {
        return gerarRelatorioProfessoresRecursivo(pessoas, 0, new StringBuilder()).toString();
    }
    
    private static StringBuilder gerarRelatorioProfessoresRecursivo(ArrayList<Pessoa> pessoas, 
                                                                  int index, StringBuilder sb) {
        if (index >= pessoas.size()) {
            return sb;
        }
        
        Pessoa pessoa = pessoas.get(index);
        if (pessoa instanceof Professor) {
            Professor professor = (Professor) pessoa;
            sb.append("=== Professor ===\n");
            sb.append("Nome: ").append(professor.getNome()).append("\n");
            sb.append("Disciplina: ").append(professor.getDisciplina()).append("\n");
            sb.append("Departamento: ").append(professor.getDepartamento()).append("\n");
            sb.append("Salário: R$ ").append(String.format("%.2f", professor.getSalario())).append("\n");
            sb.append("\n");
        }
        
        return gerarRelatorioProfessoresRecursivo(pessoas, index + 1, sb);
    }
    
    public static String gerarRelatorioGeral(ArrayList<Pessoa> pessoas) {
        return gerarRelatorioGeralRecursivo(pessoas, 0, new StringBuilder()).toString();
    }
    
    private static StringBuilder gerarRelatorioGeralRecursivo(ArrayList<Pessoa> pessoas, 
                                                            int index, StringBuilder sb) {
        if (index >= pessoas.size()) {
            return sb;
        }
        
        Pessoa pessoa = pessoas.get(index);
        sb.append("=== ").append(pessoa.getTipo()).append(" ===\n");
        sb.append("Nome: ").append(pessoa.getNome()).append("\n");
        sb.append("Idade: ").append(pessoa.getIdade()).append("\n");
        sb.append("Email: ").append(pessoa.getEmail()).append("\n");
        sb.append("Detalhes: ").append(pessoa.getDetalhes()).append("\n");
        sb.append("\n");
        
        return gerarRelatorioGeralRecursivo(pessoas, index + 1, sb);
    }
}