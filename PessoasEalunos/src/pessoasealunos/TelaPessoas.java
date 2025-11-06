/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pessoasealunos;

/**
 *
 * @author Déleo Cambula
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPessoas extends JFrame {
    private JTable tabelaPessoas;
    private DefaultTableModel modeloTabela;
    private JTextField txtNome, txtIdade, txtMatricula;
    private JComboBox<String> comboTipo;
    private JPanel painelAluno;
    private ArrayList<Pessoa> listaPessoas;
    
    public TelaPessoas() {
        initComponents();
        carregarDados();
    }
    
    private void initComponents() {
        setTitle("Sistema de Pessoas e Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        listaPessoas = new ArrayList<>();
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        
        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(2, 2, 5, 5));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar"));
        
        painelCadastro.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Pessoa", "Aluno"});
        painelCadastro.add(comboTipo);
        
        painelCadastro.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCadastro.add(txtNome);
        
        painelCadastro.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelCadastro.add(txtIdade);
        
        // Painel específico para Aluno
        painelAluno = new JPanel(new GridLayout(1, 2, 5, 5));
        painelAluno.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        painelAluno.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        painelAluno.add(txtMatricula);
        painelAluno.setVisible(false);
        
        // Painel de botões de cadastro
        JPanel painelBotoesCadastro = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar Campos");
        painelBotoesCadastro.add(btnCadastrar);
        painelBotoesCadastro.add(btnLimpar);
        
        // Painel de botões de arquivo
        JPanel painelBotoesArquivo = new JPanel(new FlowLayout());
        JButton btnSalvar = new JButton("Salvar em Arquivo");
        JButton btnCarregar = new JButton("Carregar do Arquivo");
        JButton btnLimparLista = new JButton("Limpar Lista");
        painelBotoesArquivo.add(btnSalvar);
        painelBotoesArquivo.add(btnCarregar);
        painelBotoesArquivo.add(btnLimparLista);
        
        // Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Idade");
        modeloTabela.addColumn("Detalhes");
        modeloTabela.addColumn("toString()");
        
        tabelaPessoas = new JTable(modeloTabela);
        tabelaPessoas.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabelaPessoas);
        
        // Layout
        JPanel painelSuperior = new JPanel(new BorderLayout());
        JPanel painelFormulario = new JPanel(new BorderLayout());
        
        painelFormulario.add(painelCadastro, BorderLayout.NORTH);
        painelFormulario.add(painelAluno, BorderLayout.CENTER);
        painelFormulario.add(painelBotoesCadastro, BorderLayout.SOUTH);
        
        painelSuperior.add(painelFormulario, BorderLayout.NORTH);
        painelSuperior.add(painelBotoesArquivo, BorderLayout.SOUTH);
        
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        add(painelPrincipal);
        
        // Listeners
        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarVisibilidadeCampos();
            }
        });
        
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarArquivo();
            }
        });
        
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarArquivo();
            }
        });
        
        btnLimparLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparLista();
            }
        });
    }
    
    private void atualizarVisibilidadeCampos() {
        String tipo = (String) comboTipo.getSelectedItem();
        painelAluno.setVisible("Aluno".equals(tipo));
        
        if (!"Aluno".equals(tipo)) {
            txtMatricula.setText("");
        }
    }
    
    private void cadastrar() {
        try {
            String nome = txtNome.getText();
            String tipo = (String) comboTipo.getSelectedItem();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
                return;
            }
            
            int idade = Integer.parseInt(txtIdade.getText());
            
            Pessoa pessoa;
            
            if ("Pessoa".equals(tipo)) {
                pessoa = new Pessoa(nome, idade);
            } else {
                String matricula = txtMatricula.getText();
                if (matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Matrícula é obrigatória para aluno!");
                    return;
                }
                pessoa = new Aluno(nome, idade, matricula);
            }
            
            listaPessoas.add(pessoa);
            atualizarTabela();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, 
                tipo + " cadastrado(a) com sucesso!\n" +
                "toString(): " + pessoa.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número válido!");
        }
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpar tabela
        
        // Polimorfismo: tratamos todos como Pessoa, mas cada um se comporta diferente
        for (Pessoa pessoa : listaPessoas) {
            modeloTabela.addRow(new Object[]{
                pessoa.getTipo(),        // Polimorfismo: getTipo() se comporta diferente
                pessoa.getNome(),        // Herdado de Pessoa
                pessoa.getIdade(),       // Herdado de Pessoa
                pessoa.getDetalhes(),    // Polimorfismo: getDetalhes() se comporta diferente
                pessoa.toString()        // Polimorfismo: toString() se comporta diferente
            });
        }
    }
    
    private void salvarArquivo() {
        if (listaPessoas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum dado para salvar!");
            return;
        }
        
        // Salva cada pessoa individualmente (como no exemplo original)
        boolean sucesso = true;
        for (Pessoa pessoa : listaPessoas) {
            if (!PessoaManager.gravar(pessoa)) {
                sucesso = false;
                break;
            }
        }
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, 
                "Dados salvos com sucesso!\n" +
                "Total de registros: " + listaPessoas.size() + "\n" +
                "Arquivo: Pessoas.dat");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados!");
        }
    }
    
    private void carregarArquivo() {
        listaPessoas = PessoaManager.ler();
        atualizarTabela();
        
        JOptionPane.showMessageDialog(this, 
            "Dados carregados com sucesso!\n" +
            "Total de registros: " + listaPessoas.size() + "\n" +
            "Polimorfismo: Pessoas e Alunos são tratados juntos na mesma lista!");
    }
    
    private void limparLista() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Limpar toda a lista? Esta ação não afeta o arquivo.",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            listaPessoas.clear();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Lista limpa!");
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtMatricula.setText("");
        comboTipo.setSelectedIndex(0);
        atualizarVisibilidadeCampos();
    }
    
    private void carregarDados() {
        // Carrega dados iniciais do arquivo se existirem
        listaPessoas = PessoaManager.ler();
        atualizarTabela();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPessoas().setVisible(true);
            }
        });
    }
}