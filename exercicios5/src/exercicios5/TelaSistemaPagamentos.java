/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicios5;

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

public class TelaSistemaPagamentos extends JFrame {
    private JTable tabelaPagaveis;
    private DefaultTableModel modeloTabela;
    private JTextField txtNome, txtSalario, txtValorHora, txtHoras;
    private JComboBox<String> comboTipo;
    private JPanel painelFreelancer;
    
    public TelaSistemaPagamentos() {
        initComponents();
        carregarDados();
    }
    
    private void initComponents() {
        setTitle("Sistema de Pagamentos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        
        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Pagável"));
        
        painelCadastro.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCadastro.add(txtNome);
        
        painelCadastro.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Funcionário", "Freelancer"});
        painelCadastro.add(comboTipo);
        
        painelCadastro.add(new JLabel("Salário Fixo (Funcionário):"));
        txtSalario = new JTextField();
        painelCadastro.add(txtSalario);
        
        // Painel específico para Freelancer
        painelFreelancer = new JPanel(new GridLayout(2, 2, 5, 5));
        painelFreelancer.setBorder(BorderFactory.createTitledBorder("Dados Freelancer"));
        
        painelFreelancer.add(new JLabel("Valor por Hora:"));
        txtValorHora = new JTextField();
        painelFreelancer.add(txtValorHora);
        
        painelFreelancer.add(new JLabel("Horas Trabalhadas:"));
        txtHoras = new JTextField();
        painelFreelancer.add(txtHoras);
        
        painelFreelancer.setVisible(false); // Inicialmente invisível
        
        JButton btnCadastrar = new JButton("Cadastrar");
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnCalcularTotal = new JButton("Calcular Total de Pagamentos");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        painelBotoes.add(btnCalcularTotal);
        painelBotoes.add(btnAtualizar);
        
        // Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Detalhes");
        modeloTabela.addColumn("Pagamento");
        
        tabelaPagaveis = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaPagaveis);
        
        // Layout principal
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelCadastro, BorderLayout.NORTH);
        painelSuperior.add(painelFreelancer, BorderLayout.CENTER);
        painelSuperior.add(btnCadastrar, BorderLayout.SOUTH);
        
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
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
                cadastrarPagavel();
            }
        });
        
        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotalPagamentos();
            }
        });
        
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });
    }
    
    private void atualizarVisibilidadeCampos() {
        String tipo = (String) comboTipo.getSelectedItem();
        if ("Freelancer".equals(tipo)) {
            painelFreelancer.setVisible(true);
            txtSalario.setText("");
        } else {
            painelFreelancer.setVisible(false);
            txtValorHora.setText("");
            txtHoras.setText("");
        }
    }
    
    private void cadastrarPagavel() {
        try {
            String nome = txtNome.getText();
            String tipo = (String) comboTipo.getSelectedItem();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
                return;
            }
            
            Pagavel pagavel;
            
            if ("Funcionário".equals(tipo)) {
                double salario = Double.parseDouble(txtSalario.getText());
                pagavel = new Funcionario(nome, salario);
            } else {
                double valorHora = Double.parseDouble(txtValorHora.getText());
                int horas = Integer.parseInt(txtHoras.getText());
                pagavel = new Freelancer(nome, valorHora, horas);
            }
            
            if (SistemaPagamentos.gravar(pagavel)) {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                limparCampos();
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar!");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valores numéricos inválidos!");
        }
    }
    
    private void carregarDados() {
        modeloTabela.setRowCount(0); // Limpar tabela
        ArrayList<Pagavel> pagaveis = SistemaPagamentos.ler();
        
        for (Pagavel pagavel : pagaveis) {
            String detalhes = "";
            if (pagavel instanceof Funcionario) {
                Funcionario func = (Funcionario) pagavel;
                detalhes = "Salário: MZN " + String.format("%.2f", func.getSalarioFixo());
            } else if (pagavel instanceof Freelancer) {
                Freelancer free = (Freelancer) pagavel;
                detalhes = String.format("%.1f horas × MZN %.2f/h", 
                    free.getHorasTrabalhadas(), free.getValorHora());
            }
            
            modeloTabela.addRow(new Object[]{
                pagavel.getNome(),
                pagavel.getTipo(),
                detalhes,
                "R$ " + String.format("%.2f", pagavel.calcularPagamento())
            });
        }
    }
    
    private void calcularTotalPagamentos() {
        double total = SistemaPagamentos.calcularTotalPagamentos();
        
        JOptionPane.showMessageDialog(this, 
            "Total de pagamentos: R$ " + String.format("%.2f", total));
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtSalario.setText("");
        txtValorHora.setText("");
        txtHoras.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaSistemaPagamentos().setVisible(true);
            }
        });
    }
}