/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

/**
 *
 * @author Déleo Cambula
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAutomático extends JFrame {
    private JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"Animais", "Pessoas", "Funcionários"});
    private JTextArea relatorioArea = new JTextArea(20, 40);
    private List<Object> objetos = new ArrayList<>();

    public RelatorioAutomático() {
        setTitle("Relatório Automático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Tipo de Objeto:"));
        topPanel.add(tipoComboBox);

        JButton adicionarButton = new JButton("Adicionar Objeto");
        JButton gerarButton = new JButton("Gerar Relatório");
        JButton exportarButton = new JButton("Exportar para TXT");
        JButton carregarButton = new JButton("Carregar Dados");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(gerarButton);
        buttonPanel.add(exportarButton);
        buttonPanel.add(carregarButton);

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarObjeto();
            }
        });

        gerarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });

        exportarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportarRelatorio();
            }
        });

        carregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(relatorioArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void adicionarObjeto() {
        String tipoSelecionado = (String) tipoComboBox.getSelectedItem();
        
        if (tipoSelecionado.equals("Animais")) {
            String nome = JOptionPane.showInputDialog("Nome do animal:");
            String tipo = JOptionPane.showInputDialog("Tipo do animal:");
            if (nome != null && tipo != null) {
                objetos.add(new Animal(nome, tipo));
            }
        } else if (tipoSelecionado.equals("Pessoas")) {
            String nome = JOptionPane.showInputDialog("Nome da pessoa:");
            String idadeStr = JOptionPane.showInputDialog("Idade:");
            if (nome != null && idadeStr != null) {
                try {
                    int idade = Integer.parseInt(idadeStr);
                    objetos.add(new Pessoa(nome, idade));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Idade deve ser um número!");
                }
            }
        } else if (tipoSelecionado.equals("Funcionários")) {
            String nome = JOptionPane.showInputDialog("Nome do funcionário:");
            String cargo = JOptionPane.showInputDialog("Cargo:");
            if (nome != null && cargo != null) {
                objetos.add(new Funcionario(nome, cargo));
            }
        }
    }

    private String gerarRelatorioRecursivo(List<Object> lista, int index, String tipoFiltro) {
        if (index >= lista.size()) {
            return "";
        }
        
        Object obj = lista.get(index);
        String linha = "";
        
        if (tipoFiltro.equals("Animais") && obj instanceof Animal) {
            linha = obj.toString() + "\n";
        } else if (tipoFiltro.equals("Pessoas") && obj instanceof Pessoa) {
            linha = obj.toString() + "\n";
        } else if (tipoFiltro.equals("Funcionários") && obj instanceof Funcionario) {
            linha = obj.toString() + "\n";
        } else if (tipoFiltro.equals("Todos")) {
            linha = obj.toString() + "\n";
        }
        
        return linha + gerarRelatorioRecursivo(lista, index + 1, tipoFiltro);
    }

    private void gerarRelatorio() {
        String tipoFiltro = (String) tipoComboBox.getSelectedItem();
        relatorioArea.setText("=== RELATÓRIO DE " + tipoFiltro.toUpperCase() + " ===\n\n");
        String relatorio = gerarRelatorioRecursivo(objetos, 0, tipoFiltro);
        relatorioArea.append(relatorio);
        
        if (relatorio.trim().isEmpty()) {
            relatorioArea.append("Nenhum objeto encontrado para este tipo.");
        }
    }

    private void exportarRelatorio() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileChooser.getSelectedFile() + ".txt"))) {
                writer.write(relatorioArea.getText());
                JOptionPane.showMessageDialog(this, "Relatório exportado com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar relatório!");
            }
        }
    }

    private void carregarDados() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                objetos = (List<Object>) ois.readObject();
                JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar dados!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RelatorioAutomático().setVisible(true);
            }
        });
    }
}