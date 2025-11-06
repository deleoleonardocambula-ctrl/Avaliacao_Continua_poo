/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio6;

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

public class TelaBanco extends JFrame {
    private JTable tabelaContas;
    private DefaultTableModel modeloTabela;
    private JTextField txtNumero, txtSaldo;
    private JComboBox<String> comboTipo;
    
    public TelaBanco() {
        initComponents();
        carregarDados();
    }
    
    private void initComponents() {
        setTitle("Sistema Bancário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        
        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(2, 2, 5, 5));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Conta"));
        
        painelCadastro.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        painelCadastro.add(txtNumero);
        
        painelCadastro.add(new JLabel("Saldo:"));
        txtSaldo = new JTextField();
        painelCadastro.add(txtSaldo);
        
        painelCadastro.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Corrente", "Poupança"});
        painelCadastro.add(comboTipo);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        painelCadastro.add(btnCadastrar);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnCalcularTaxas = new JButton("Calcular Taxas Totais");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        painelBotoes.add(btnCalcularTaxas);
        painelBotoes.add(btnAtualizar);
        
        // Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Número");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Saldo");
        modeloTabela.addColumn("Taxa");
        
        tabelaContas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaContas);
        
        // Adicionar componentes ao painel principal
        painelPrincipal.add(painelCadastro, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
        
        // Listeners
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarConta();
            }
        });
        
        btnCalcularTaxas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTaxasTotais();
            }
        });
        
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });
    }
    
    private void cadastrarConta() {
        try {
            String numero = txtNumero.getText();
            double saldo = Double.parseDouble(txtSaldo.getText());
            String tipo = (String) comboTipo.getSelectedItem();
            
            Conta conta;
            if ("Corrente".equals(tipo)) {
                conta = new ContaCorrente(numero, saldo);
            } else {
                conta = new ContaPoupanca(numero, saldo);
            }
            
            if (Banco.gravar(conta)) {
                JOptionPane.showMessageDialog(this, "Conta cadastrada com sucesso!");
                limparCampos();
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar conta!");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Saldo deve ser um número válido!");
        }
    }
    
    private void carregarDados() {
        modeloTabela.setRowCount(0); // Limpar tabela
        ArrayList<Conta> contas = Banco.ler();
        
        for (Conta conta : contas) {
            modeloTabela.addRow(new Object[]{
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo(),
                conta.calcularTaxa()
            });
        }
    }
    
    private void calcularTaxasTotais() {
        ArrayList<Conta> contas = Banco.ler();
        double totalTaxas = Banco.calcularTaxasTotais(contas);
        
        JOptionPane.showMessageDialog(this, 
            "Total de taxas: R$ " + String.format("%.2f", totalTaxas));
    }
    
    private void limparCampos() {
        txtNumero.setText("");
        txtSaldo.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaBanco().setVisible(true);
            }
        });
    }
}