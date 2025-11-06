/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Déleo Cambula
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Usuario;

public class TelaGerirUsuarios extends JFrame {

    private JTable tabelaUsuarios;
    private JButton btnAdicionar, btnEditar, btnApagar, btnVoltar;
    private JTextField campoNome, campoSenha, campoIdade;
    private JCheckBox checkAdmin, checkGerente, checkVendedor;
    private DefaultTableModel tableModel;
    private int idSelecionado = -1;
    private Usuario usuarioLogado;

    public TelaGerirUsuarios(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        // Configuração básica da janela
        setTitle("Sistema de Gestão de Usuários - Shigom");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Cabeçalho com nome do usuário e botão voltar
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(Color.WHITE);
        
        JLabel lblUsuario = new JLabel("Usuário: " + usuario.getNome() + " (" + usuario.getNivelDeAcesso() + ")");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsuario.setForeground(new Color(70, 130, 180));
        
        btnVoltar = criarBotao("Voltar ao Menu", new Color(70, 130, 180));
        
        painelCabecalho.add(lblUsuario, BorderLayout.WEST);
        painelCabecalho.add(btnVoltar, BorderLayout.EAST);
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        // Painel central (formulário + tabela)
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);

        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        // Campos do formulário
        adicionarCampo(painelFormulario, "Nome:", campoNome = new JTextField());
        adicionarCampo(painelFormulario, "Senha:", campoSenha = new JTextField());
        adicionarCampo(painelFormulario, "Idade:", campoIdade = new JTextField());

        // Checkboxes para nível de acesso
        JPanel painelNivelAcesso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelNivelAcesso.setBackground(Color.WHITE);
        JLabel lblNivelAcesso = new JLabel("Nível de Acesso:");
        lblNivelAcesso.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNivelAcesso.setForeground(new Color(70, 70, 70));
        
        checkAdmin = new JCheckBox("Admin");
        checkGerente = new JCheckBox("Gerente");
        checkVendedor = new JCheckBox("Vendedor");
        
        // Estilização dos checkboxes
        Font fontCheck = new Font("Segoe UI", Font.PLAIN, 14);
        checkAdmin.setFont(fontCheck);
        checkGerente.setFont(fontCheck);
        checkVendedor.setFont(fontCheck);
        
        checkAdmin.setBackground(Color.WHITE);
        checkGerente.setBackground(Color.WHITE);
        checkVendedor.setBackground(Color.WHITE);
        
        painelNivelAcesso.add(lblNivelAcesso);
        painelNivelAcesso.add(checkAdmin);
        painelNivelAcesso.add(checkGerente);
        painelNivelAcesso.add(checkVendedor);
        
        painelFormulario.add(new JLabel()); // Espaço vazio para alinhamento
        painelFormulario.add(painelNivelAcesso);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(Color.WHITE);
        
        btnAdicionar = criarBotao("Adicionar", new Color(70, 130, 180));
        btnEditar = criarBotao("Editar", new Color(70, 130, 180));
        btnApagar = criarBotao("Apagar", new Color(70, 130, 180));

        btnEditar.setEnabled(false);
        btnApagar.setEnabled(false);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnApagar);

        // Tabela de usuários
        String[] colunas = {"ID", "Nome", "Nível de Acesso", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaUsuarios = new JTable(tableModel);
        configurarTabela(tabelaUsuarios);

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));

        // Container para formulário e botões
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelFormulario, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        // Adicionando componentes ao painel central
        painelCentral.add(painelSuperior, BorderLayout.NORTH);
        painelCentral.add(scrollPane, BorderLayout.CENTER);

        // Adicionando ao painel principal
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        this.add(painelPrincipal);
        
        // Carrega os dados
        carregarUsuarios();
        
        // Configura listeners
        configurarListeners();
    }

    private void adicionarCampo(JPanel painel, String label, JTextField campo) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(new Color(70, 70, 70));
        painel.add(lbl);
        painel.add(campo);
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setBorder(new EmptyBorder(8, 20, 8, 20));
        
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });
        
        return botao;
    }

    private void configurarTabela(JTable tabela) {
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setBackground(Color.WHITE);
        tabela.setFillsViewportHeight(true);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    private void carregarUsuarios() {
        tableModel.setRowCount(0);
        ArrayList<Usuario> usuarios = Usuario.lerUsuario();
        
        if (usuarios != null) {
            for (Usuario u : usuarios) {
                if(u.getStatus() != false){
                    tableModel.addRow(new Object[]{
                        u.getId(),
                        u.getNome(),
//                        u.getIdade(),
                        u.getNivelDeAcesso(),
                        u.getStatus() ? "Ativo" : "Inativo"
                    });
                }
            }
        }
    }

    private String obterNivelAcessoSelecionado() {
        if (checkAdmin.isSelected()) return "Admin";
        if (checkGerente.isSelected()) return "Professor";
        if (checkVendedor.isSelected()) return "Gestor";
        return "";
    }

    private void limparCheckboxes() {
        checkAdmin.setSelected(false);
        checkGerente.setSelected(false);
        checkVendedor.setSelected(false);
    }

    private void configurarListeners() {
        // Listener para seleção na tabela
        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaUsuarios.getSelectedRow() != -1) {
                int linha = tabelaUsuarios.getSelectedRow();
                idSelecionado = (int) tabelaUsuarios.getValueAt(linha, 0);
                
                campoNome.setText(tabelaUsuarios.getValueAt(linha, 1).toString());
                campoSenha.setText(""); // Não mostra a senha por segurança
                
                // Configura o nível de acesso
                String nivel = tabelaUsuarios.getValueAt(linha, 2).toString();
                limparCheckboxes();
                switch(nivel) {
                    case "Admin": checkAdmin.setSelected(true); break;
                    case "Gestor": checkGerente.setSelected(true); break;
                    case "Professor": checkVendedor.setSelected(true); break;
                }
                
                btnAdicionar.setEnabled(false);
                btnEditar.setEnabled(true);
                btnApagar.setEnabled(true);
            }
        });
        
        // Listener para o botão voltar
        btnVoltar.addActionListener(e -> {
            new TelaMenuPrincipal(usuarioLogado).setVisible(true);
            dispose();
        });
        
        // Listener para adicionar usuário
        btnAdicionar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String senha = campoSenha.getText().trim();
            String nivelAcesso = obterNivelAcessoSelecionado();
            int idade = Integer.parseInt(campoIdade.getText());
            if (nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e senha são obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (nivelAcesso.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione um nível de acesso", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(Usuario.usuarioJaExiste(nome)){
               JOptionPane.showMessageDialog(this, "Este usuario já existe", "Erro", JOptionPane.ERROR_MESSAGE);
               return;
            }
            
            Usuario.CriarUsuario(nome, idade, senha, nivelAcesso);
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            carregarUsuarios();
            limparCampos();
        });
        
        // Listener para editar usuário
        btnEditar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String nivelAcesso = obterNivelAcessoSelecionado();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (nivelAcesso.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione um nível de acesso", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ArrayList<Usuario> usuarios = Usuario.lerUsuario();
            for (Usuario u : usuarios) {
                if (u.getId() == idSelecionado) {
                    u.setNome(nome);
                    u.setNivelDeAcesso(nivelAcesso);
                    Usuario.editarUsuario(u);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarUsuarios();
            limparCampos();
        });
        
        // Listener para apagar usuário
        btnApagar.addActionListener(e -> {
            int confirmacao = JOptionPane.showConfirmDialog(
                this, 
                "Tem certeza que deseja desativar este usuário?", 
                "Confirmar Desativação", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                ArrayList<Usuario> usuarios = Usuario.lerUsuario();
                for (Usuario u : usuarios) {
                    if (u.getId() == idSelecionado) {
                        u.setStatus(false);
                        Usuario.editarUsuario(u);
                        break;
                    }
                }
                
                JOptionPane.showMessageDialog(this, "Usuário desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarUsuarios();
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        campoNome.setText("");
        campoSenha.setText("");
        limparCheckboxes();
        idSelecionado = -1;
        
        tabelaUsuarios.clearSelection();
        btnAdicionar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnApagar.setEnabled(false);
    }

}