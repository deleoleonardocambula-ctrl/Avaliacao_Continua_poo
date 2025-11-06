/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import model.Usuario;

/**
 *
 * @author Déleo Cambula
 */
import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        // Configuração básica da janela
        setTitle("Sistema de Login - Shigom");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null); // Layout nulo para posicionamento absoluto

        // Fundo branco
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Sistema de Gestão");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 130, 180));
        titulo.setBounds(120, 30, 250, 30);
        add(titulo);

        // Label Usuário
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsuario.setBounds(50, 90, 100, 20);
        add(lblUsuario);

        // Campo Usuário
        JTextField txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBounds(120, 90, 250, 30);
        add(txtUsuario);

        // Label Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSenha.setBounds(50, 140, 100, 20);
        add(lblSenha);

        // Campo Senha
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSenha.setBounds(120, 140, 250, 30);
        add(txtSenha);

        // Botão Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancelar.setBounds(120, 200, 120, 35);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setForeground(new Color(70, 70, 70));
        add(btnCancelar);

        // Botão Entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setBounds(250, 200, 120, 35);
        btnEntrar.setBackground(new Color(70, 130, 180));
        btnEntrar.setForeground(Color.WHITE);
        add(btnEntrar);

        // Ações dos botões
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else { 
                Usuario usuarioLogado = Usuario.login(usuario, senha);
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
                    new TelaMenuPrincipal(usuarioLogado).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> System.exit(0));
    }

    
}
