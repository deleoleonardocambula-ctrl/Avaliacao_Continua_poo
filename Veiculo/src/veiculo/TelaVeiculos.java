/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veiculo;

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

public class TelaVeiculos extends JFrame {
    private JTable tabelaVeiculos;
    private DefaultTableModel modeloTabela;
    private JTextField txtMarca, txtModelo, txtAno, txtCombustivel, txtMarchas;
    private JComboBox<String> comboTipo;
    private JPanel painelCarro, painelBicicleta, painelCamposEspecificos;
    private JTextArea textAreaMovimentos;
    
    public TelaVeiculos() {
        initComponents();
        carregarDadosTabela();
    }
    
    private void initComponents() {
        setTitle("Sistema de Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Painel principal com abas
        JTabbedPane abas = new JTabbedPane();
        
        // Aba 1: Cadastro e Lista
        abas.addTab("Cadastro e Lista", criarAbaCadastro());
        
        // Aba 2: Movimentos Recursivos
        abas.addTab("Movimentos Recursivos", criarAbaMovimentos());
        
        add(abas);
    }
    
    private JPanel criarAbaCadastro() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de cadastro básico
        JPanel painelCadastro = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Dados Básicos do Veículo"));
        
        painelCadastro.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Carro", "Bicicleta"});
        painelCadastro.add(comboTipo);
        
        painelCadastro.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        painelCadastro.add(txtMarca);
        
        painelCadastro.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelCadastro.add(txtModelo);
        
        painelCadastro.add(new JLabel("Ano:"));
        txtAno = new JTextField();
        painelCadastro.add(txtAno);
        
        // Painel para campos específicos (será dinâmico)
        painelCamposEspecificos = new JPanel(new BorderLayout());
        
        // Painel específico para Carro
        painelCarro = new JPanel(new GridLayout(1, 2, 5, 5));
        painelCarro.setBorder(BorderFactory.createTitledBorder("Dados do Carro"));
        painelCarro.add(new JLabel("Combustível:"));
        txtCombustivel = new JTextField();
        painelCarro.add(txtCombustivel);
        
        // Painel específico para Bicicleta
        painelBicicleta = new JPanel(new GridLayout(1, 2, 5, 5));
        painelBicicleta.setBorder(BorderFactory.createTitledBorder("Dados da Bicicleta"));
        painelBicicleta.add(new JLabel("Número de Marchas:"));
        txtMarchas = new JTextField();
        painelBicicleta.add(txtMarchas);
        
        JButton btnCadastrar = new JButton("Cadastrar Veículo");
        
        // Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Marca");
        modeloTabela.addColumn("Modelo");
        modeloTabela.addColumn("Ano");
        modeloTabela.addColumn("Detalhes");
        
        tabelaVeiculos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaVeiculos);
        
        // Layout
        JPanel painelSuperior = new JPanel(new BorderLayout());
        JPanel painelFormulario = new JPanel(new BorderLayout());
        
        painelFormulario.add(painelCadastro, BorderLayout.NORTH);
        painelFormulario.add(painelCamposEspecificos, BorderLayout.CENTER);
        
        painelSuperior.add(painelFormulario, BorderLayout.NORTH);
        painelSuperior.add(btnCadastrar, BorderLayout.SOUTH);
        
        painel.add(painelSuperior, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
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
                cadastrarVeiculo();
            }
        });
        
        // Configuração inicial - mostrar campos do Carro por padrão
        atualizarVisibilidadeCampos();
        
        return painel;
    }
    
    private JPanel criarAbaMovimentos() {
        JPanel painel = new JPanel(new BorderLayout());
        
        textAreaMovimentos = new JTextArea();
        textAreaMovimentos.setEditable(false);
        textAreaMovimentos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollTextArea = new JScrollPane(textAreaMovimentos);
        
        JButton btnMostrarTodos = new JButton("Mostrar Todos os Movimentos (Recursivo)");
        btnMostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMovimentosRecursivos();
            }
        });
        
        JPanel painelInfo = new JPanel(new BorderLayout());
        painelInfo.add(new JLabel("Movimentos dos Veículos (usando recursividade):"), BorderLayout.NORTH);
        painelInfo.add(new JLabel("O método recursivo percorre cada veículo e mostra seu movimento específico"), BorderLayout.SOUTH);
        
        painel.add(painelInfo, BorderLayout.NORTH);
        painel.add(scrollTextArea, BorderLayout.CENTER);
        painel.add(btnMostrarTodos, BorderLayout.SOUTH);
        
        return painel;
    }
    
    private void atualizarVisibilidadeCampos() {
        String tipo = (String) comboTipo.getSelectedItem();
        
        // Limpa o painel de campos específicos
        painelCamposEspecificos.removeAll();
        
        // Adiciona o painel correto baseado no tipo selecionado
        if ("Carro".equals(tipo)) {
            painelCamposEspecificos.add(painelCarro, BorderLayout.CENTER);
        } else {
            painelCamposEspecificos.add(painelBicicleta, BorderLayout.CENTER);
        }
        
        // Atualiza a interface
        painelCamposEspecificos.revalidate();
        painelCamposEspecificos.repaint();
    }
    
    private void cadastrarVeiculo() {
        try {
            String tipo = (String) comboTipo.getSelectedItem();
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            
            if (marca.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Marca e modelo são obrigatórios!");
                return;
            }
            
            int ano = Integer.parseInt(txtAno.getText());
            
            Veiculo veiculo;
            
            if ("Carro".equals(tipo)) {
                String combustivel = txtCombustivel.getText();
                if (combustivel.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tipo de combustível é obrigatório!");
                    return;
                }
                veiculo = new Carro(marca, modelo, ano, combustivel);
            } else {
                int marchas = Integer.parseInt(txtMarchas.getText());
                veiculo = new Bicicleta(marca, modelo, ano, marchas);
            }
            
            if (VeiculoManager.gravar(veiculo)) {
                JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
                limparCampos();
                carregarDadosTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo!");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e marchas devem ser números válidos!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
    
    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0);
        ArrayList<Veiculo> veiculos = VeiculoManager.ler();
        
        for (Veiculo veiculo : veiculos) {
            String detalhes = "";
            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                detalhes = "Combustível: " + carro.getTipoCombustivel();
            } else if (veiculo instanceof Bicicleta) {
                Bicicleta bike = (Bicicleta) veiculo;
                detalhes = "Marchas: " + bike.getNumeroMarchas();
            }
            
            modeloTabela.addRow(new Object[]{
                veiculo.getClass().getSimpleName(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAno(),
                detalhes
            });
        }
    }
    
    private void mostrarMovimentosRecursivos() {
        ArrayList<Veiculo> veiculos = VeiculoManager.ler();
        
        if (veiculos.isEmpty()) {
            textAreaMovimentos.setText("Nenhum veículo cadastrado.");
            return;
        }
        
        String resultado = VeiculoManager.mostrarTodosRecursivo(veiculos);
        textAreaMovimentos.setText(resultado);
        
        JOptionPane.showMessageDialog(this, 
            "Total de veículos: " + veiculos.size() + 
            "\nListagem recursiva concluída!\n\n" +
            "Como funciona a recursividade");
    }
    
    private void limparCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAno.setText("");
        txtCombustivel.setText("");
        txtMarchas.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaVeiculos().setVisible(true);
            }
        });
    }
}