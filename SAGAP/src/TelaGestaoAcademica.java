/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Déleo Cambula
 */
// TelaGestaoAcademica.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class TelaGestaoAcademica extends JFrame {
    private JTable tabelaPessoas;
    private DefaultTableModel modeloTabela;
    private JTextArea textAreaRelatorios;
    private ArrayList<Pessoa> listaPessoas;
    
    // Campos para Pessoa
    private JTextField txtNome, txtIdade, txtEmail, txtTelefone;
    
    // Campos para Aluno
    private JTextField txtMatricula, txtCurso, txtMensalidade, txtNotaFinal;
    private JComboBox<String> comboTipoAluno;
    
    // Campos para Professor
    private JTextField txtDisciplina, txtSalario, txtDepartamento;
    
    private JComboBox<String> comboTipoPessoa;
    private JPanel painelAluno, painelProfessor;
    
    public TelaGestaoAcademica() {
        initComponents();
        carregarDados();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão Acadêmica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        listaPessoas = new ArrayList<>();
        
        // Painel principal com abas
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Cadastro", criarAbaCadastro());
        abas.addTab("Lista e Notas", criarAbaLista());
        abas.addTab("Relatórios", criarAbaRelatorios());
        
        add(abas);
    }
    
    private JPanel criarAbaCadastro() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de dados básicos
        JPanel painelBasico = new JPanel(new GridLayout(4, 2, 5, 5));
        painelBasico.setBorder(BorderFactory.createTitledBorder("Dados Básicos"));
        
        painelBasico.add(new JLabel("Tipo:"));
        comboTipoPessoa = new JComboBox<>(new String[]{"Aluno", "Professor"});
        painelBasico.add(comboTipoPessoa);
        
        painelBasico.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelBasico.add(txtNome);
        
        painelBasico.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelBasico.add(txtIdade);
        
        painelBasico.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelBasico.add(txtEmail);
        
        painelBasico.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelBasico.add(txtTelefone);
        
        // Painel para Aluno
        painelAluno = new JPanel(new GridLayout(5, 2, 5, 5));
        painelAluno.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        
        painelAluno.add(new JLabel("Tipo de Aluno:"));
        comboTipoAluno = new JComboBox<>(new String[]{"Bolseiro", "Não-Bolseiro"});
        painelAluno.add(comboTipoAluno);
        
        painelAluno.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        painelAluno.add(txtMatricula);
        
        painelAluno.add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        painelAluno.add(txtCurso);
        
        painelAluno.add(new JLabel("Mensalidade:"));
        txtMensalidade = new JTextField();
        painelAluno.add(txtMensalidade);
        
        painelAluno.add(new JLabel("Nota Final:"));
        txtNotaFinal = new JTextField("0.0");
        painelAluno.add(txtNotaFinal);
        
        // Painel para Professor
        painelProfessor = new JPanel(new GridLayout(3, 2, 5, 5));
        painelProfessor.setBorder(BorderFactory.createTitledBorder("Dados do Professor"));
        
        painelProfessor.add(new JLabel("Disciplina:"));
        txtDisciplina = new JTextField();
        painelProfessor.add(txtDisciplina);
        
        painelProfessor.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        painelProfessor.add(txtSalario);
        
        painelProfessor.add(new JLabel("Departamento:"));
        txtDepartamento = new JTextField();
        painelProfessor.add(txtDepartamento);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar Campos");
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLimpar);
        
        // Layout
        JPanel painelCamposEspecificos = new JPanel(new BorderLayout());
        painelCamposEspecificos.add(painelAluno, BorderLayout.NORTH);
        
        JPanel painelFormulario = new JPanel(new BorderLayout());
        painelFormulario.add(painelBasico, BorderLayout.NORTH);
        painelFormulario.add(painelCamposEspecificos, BorderLayout.CENTER);
        painelFormulario.add(painelBotoes, BorderLayout.SOUTH);
        
        painel.add(painelFormulario, BorderLayout.NORTH);
        
        // Listeners
        comboTipoPessoa.addActionListener(e -> atualizarVisibilidadeCampos());
        btnCadastrar.addActionListener(e -> cadastrarPessoa());
        btnLimpar.addActionListener(e -> limparCampos());
        
        atualizarVisibilidadeCampos();
        return painel;
    }
    
    private JPanel criarAbaLista() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Detalhes");
        modeloTabela.addColumn("Nota");
        modeloTabela.addColumn("Pagamento");
        modeloTabela.addColumn("Dispensa");
        
        tabelaPessoas = new JTable(modeloTabela);
        tabelaPessoas.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabelaPessoas);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAtualizarNota = new JButton("Atualizar Nota");
        JButton btnCarregar = new JButton("Carregar do Arquivo");
        JButton btnSalvar = new JButton("Salvar no Arquivo");
        
        painelBotoes.add(btnAtualizarNota);
        painelBotoes.add(btnCarregar);
        painelBotoes.add(btnSalvar);
        
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAtualizarNota.addActionListener(e -> atualizarNota());
        btnCarregar.addActionListener(e -> carregarArquivo());
        btnSalvar.addActionListener(e -> salvarArquivo());
        
        return painel;
    }
    
    private JPanel criarAbaRelatorios() {
        JPanel painel = new JPanel(new BorderLayout());
        
        textAreaRelatorios = new JTextArea();
        textAreaRelatorios.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textAreaRelatorios);
        
        // Painel de botões de relatório
        JPanel painelBotoesRelatorios = new JPanel(new FlowLayout());
        JButton btnRelatorioAlunos = new JButton("Relatório de Alunos (Recursivo)");
        JButton btnRelatorioProfessores = new JButton("Relatório de Professores (Recursivo)");
        JButton btnRelatorioGeral = new JButton("Relatório Geral (Recursivo)");
        JButton btnExportar = new JButton("Exportar para TXT");
        
        painelBotoesRelatorios.add(btnRelatorioAlunos);
        painelBotoesRelatorios.add(btnRelatorioProfessores);
        painelBotoesRelatorios.add(btnRelatorioGeral);
        painelBotoesRelatorios.add(btnExportar);
        
        painel.add(new JLabel("Relatórios Gerados com Recursividade:"), BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelBotoesRelatorios, BorderLayout.SOUTH);
        
        // Listeners
        btnRelatorioAlunos.addActionListener(e -> gerarRelatorioAlunos());
        btnRelatorioProfessores.addActionListener(e -> gerarRelatorioProfessores());
        btnRelatorioGeral.addActionListener(e -> gerarRelatorioGeral());
        btnExportar.addActionListener(e -> exportarRelatorio());
        
        return painel;
    }
    
    private void atualizarVisibilidadeCampos() {
        String tipo = (String) comboTipoPessoa.getSelectedItem();
        
        Container parent = painelAluno.getParent();
        if (parent instanceof JPanel) {
            JPanel painelPai = (JPanel) parent;
            painelPai.removeAll();
            
            if ("Aluno".equals(tipo)) {
                painelPai.add(painelAluno, BorderLayout.NORTH);
            } else {
                painelPai.add(painelProfessor, BorderLayout.NORTH);
            }
            
            painelPai.revalidate();
            painelPai.repaint();
        }
    }
    
    private void cadastrarPessoa() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String email = txtEmail.getText();
            String telefone = txtTelefone.getText();
            String tipo = (String) comboTipoPessoa.getSelectedItem();
            
            Pessoa pessoa;
            
            if ("Aluno".equals(tipo)) {
                String matricula = txtMatricula.getText();
                String curso = txtCurso.getText();
                double mensalidade = Double.parseDouble(txtMensalidade.getText());
                double notaFinal = Double.parseDouble(txtNotaFinal.getText());
                String tipoAluno = (String) comboTipoAluno.getSelectedItem();
                
                if ("Bolseiro".equals(tipoAluno)) {
                    pessoa = new AlunoBolseiro(nome, idade, email, telefone, matricula, curso, mensalidade);
                } else {
                    pessoa = new AlunoNaoBolseiro(nome, idade, email, telefone, matricula, curso, mensalidade);
                }
                ((Aluno) pessoa).setNotaFinal(notaFinal);
                
            } else {
                String disciplina = txtDisciplina.getText();
                double salario = Double.parseDouble(txtSalario.getText());
                String departamento = txtDepartamento.getText();
                
                pessoa = new Professor(nome, idade, email, telefone, disciplina, salario, departamento);
            }
            
            if (GestorAcademico.gravar(pessoa)) {
                JOptionPane.showMessageDialog(this, tipo + " cadastrado(a) com sucesso!");
                limparCampos();
                carregarDados();
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
    
    private void atualizarNota() {
        int linhaSelecionada = tabelaPessoas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela!");
            return;
        }
        
        Pessoa pessoa = listaPessoas.get(linhaSelecionada);
        if (!(pessoa instanceof Aluno)) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!");
            return;
        }
        
        String notaStr = JOptionPane.showInputDialog(this, "Digite a nova nota final:");
        try {
            double nota = Double.parseDouble(notaStr);
            if (nota < 0 || nota > 20) {
                JOptionPane.showMessageDialog(this, "Nota deve estar entre 0 e 20!");
                return;
            }
            
            ((Aluno) pessoa).setNotaFinal(nota);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Nota atualizada com sucesso!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nota inválida!");
        }
    }
    
    private void carregarDados() {
        listaPessoas = GestorAcademico.ler();
        atualizarTabela();
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa instanceof Aluno) {
                Aluno aluno = (Aluno) pessoa;
                modeloTabela.addRow(new Object[]{
                    aluno.getTipo(),
                    aluno.getNome(),
                    aluno.getCurso() + " - " + aluno.getMatricula(),
                    String.format("%.1f", aluno.getNotaFinal()),
                    String.format("R$ %.2f", aluno.calcularPagamento()),
                    aluno.isDispensado() ? "Sim" : "Não"
                });
            } else if (pessoa instanceof Professor) {
                Professor professor = (Professor) pessoa;
                modeloTabela.addRow(new Object[]{
                    professor.getTipo(),
                    professor.getNome(),
                    professor.getDisciplina() + " - " + professor.getDepartamento(),
                    "N/A",
                    String.format("R$ %.2f", professor.getSalario()),
                    "N/A"
                });
            }
        }
    }
    
    private void salvarArquivo() {
        if (GestorAcademico.salvarLista(listaPessoas)) {
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados!");
        }
    }
    
    private void carregarArquivo() {
        carregarDados();
        JOptionPane.showMessageDialog(this, 
            "Dados carregados!\nTotal: " + listaPessoas.size() + " registros");
    }
    
    private void gerarRelatorioAlunos() {
        String relatorio = GestorAcademico.gerarRelatorioAlunos(listaPessoas);
        textAreaRelatorios.setText("=== RELATÓRIO DE ALUNOS (RECURSIVO) ===\n\n" + relatorio);
    }
    
    private void gerarRelatorioProfessores() {
        String relatorio = GestorAcademico.gerarRelatorioProfessores(listaPessoas);
        textAreaRelatorios.setText("=== RELATÓRIO DE PROFESSORES (RECURSIVO) ===\n\n" + relatorio);
    }
    
    private void gerarRelatorioGeral() {
        String relatorio = GestorAcademico.gerarRelatorioGeral(listaPessoas);
        textAreaRelatorios.setText("=== RELATÓRIO GERAL (RECURSIVO) ===\n\n" + relatorio);
    }
    
    private void exportarRelatorio() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_academico.txt"))) {
            writer.write(textAreaRelatorios.getText());
            JOptionPane.showMessageDialog(this, "Relatório exportado para 'relatorio_academico.txt'");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar: " + ex.getMessage());
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtMatricula.setText("");
        txtCurso.setText("");
        txtMensalidade.setText("");
        txtNotaFinal.setText("0.0");
        txtDisciplina.setText("");
        txtSalario.setText("");
        txtDepartamento.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaGestaoAcademica().setVisible(true));
    }
}