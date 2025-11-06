/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionariosegerentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Déleo Cambula
 */


public class CadastroFuncionarios extends JFrame {
    private FuncionarioTableModel tableModel = new FuncionarioTableModel();
    private JTable table = new JTable(tableModel);
    private JTextField nomeField = new JTextField(20);
    private JTextField salarioField = new JTextField(10);
    private JCheckBox gerenteCheckBox = new JCheckBox("É gerente?");

    public CadastroFuncionarios() {
        setTitle("Cadastro de Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Salário:"));
        inputPanel.add(salarioField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(gerenteCheckBox);

        JButton calcularButton = new JButton("Calcular Folha Total");
        JButton salvarArquivoButton = new JButton("Salvar");
        JButton carregarButton = new JButton("Carregar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calcularButton);
        buttonPanel.add(salvarArquivoButton);
        buttonPanel.add(carregarButton);

        salvarArquivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarFuncionario();
            }
        });

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularFolhaTotal();
            }
        });

        salvarArquivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarFuncionario();
            }
        });

        carregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarFuncionarios();
            }
        });

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void salvarFuncionario() {
        try {
            String nome = nomeField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            
            if (gerenteCheckBox.isSelected()) {
                salvar(new Gerente(nome, salario));
            } else {
                salvar(new Funcionario(nome, salario));
            }
            
            nomeField.setText("");
            salarioField.setText("");
            gerenteCheckBox.setSelected(false);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salário deve ser um número!");
        }
    }

    private double calcularFolhaRecursivo(List<Funcionario> funcionarios, int index) {
        if (index >= funcionarios.size()) {
            return 0;
        }
        return funcionarios.get(index).getSalario() + calcularFolhaRecursivo(funcionarios, index + 1);
    }

    private void calcularFolhaTotal() {
        double total = calcularFolhaRecursivo(tableModel.getFuncionarios(), 0);
        JOptionPane.showMessageDialog(this, "Folha total: R$ " + String.format("%.2f", total));
    }

    private void salvar(Funcionario funcionario) {
        Funcionario.gravar(funcionario);
        JOptionPane.showMessageDialog(this, "Funcionario salvo");
        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        List<Funcionario> funcionarios = Funcionario.ler();
        tableModel.setFuncionarios(funcionarios);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionarios().setVisible(true);
            }
        });
    }
}