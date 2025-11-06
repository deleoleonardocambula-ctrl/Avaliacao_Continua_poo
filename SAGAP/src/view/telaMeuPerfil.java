/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.Usuario;

/**
 *
 * @author Déleo Cambula
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaMeuPerfil extends JFrame {
    
    private Usuario usuarioLogado;
    
    public telaMeuPerfil(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        // Configuração básica da janela
        setTitle("Meu Perfil - Sistema de Gestão");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null); // Layout nulo para posicionamento absoluto

        // Fundo branco
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Meu Perfil");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 130, 180));
        titulo.setBounds(180, 20, 200, 30);
        add(titulo);

        // Informações do usuário
        JLabel lblNomeUsuario = new JLabel("Nome: " + usuario.getNome());
        lblNomeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNomeUsuario.setForeground(new Color(70, 70, 70));
        lblNomeUsuario.setBounds(50, 70, 400, 20);
        add(lblNomeUsuario);

        JLabel lblNivelAcesso = new JLabel("Nível de Acesso: " + usuario.getNivelDeAcesso());
        lblNivelAcesso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNivelAcesso.setForeground(new Color(70, 70, 70));
        lblNivelAcesso.setBounds(50, 100, 400, 20);
        add(lblNivelAcesso);

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(50, 130, 400, 2);
        separador.setForeground(new Color(70, 130, 180));
        add(separador);

        // Formulário para trocar senha
        JLabel lblTrocarSenha = new JLabel("Trocar Senha");
        lblTrocarSenha.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTrocarSenha.setForeground(new Color(70, 130, 180));
        lblTrocarSenha.setBounds(50, 150, 200, 20);
        add(lblTrocarSenha);

        JLabel lblSenhaAtual = new JLabel("Senha Atual:");
        lblSenhaAtual.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSenhaAtual.setBounds(50, 180, 100, 20);
        add(lblSenhaAtual);

        JPasswordField txtSenhaAtual = new JPasswordField();
        txtSenhaAtual.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSenhaAtual.setBounds(160, 180, 250, 25);
        add(txtSenhaAtual);

        JLabel lblNovaSenha = new JLabel("Nova Senha:");
        lblNovaSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNovaSenha.setBounds(50, 220, 100, 20);
        add(lblNovaSenha);

        JPasswordField txtNovaSenha = new JPasswordField();
        txtNovaSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNovaSenha.setBounds(160, 220, 250, 25);
        add(txtNovaSenha);

        // Botões
        JButton btnTrocarSenha = new JButton("Trocar Senha");
        btnTrocarSenha.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTrocarSenha.setBounds(160, 270, 150, 35);
        btnTrocarSenha.setBackground(new Color(70, 130, 180));
        btnTrocarSenha.setForeground(Color.WHITE);
        add(btnTrocarSenha);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBounds(320, 270, 90, 35);
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(70, 70, 70));
        btnVoltar.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        add(btnVoltar);

        // Ação do botão Trocar Senha
        btnTrocarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senhaAtual = new String(txtSenhaAtual.getPassword());
                String novaSenha = new String(txtNovaSenha.getPassword());
                
                if (senhaAtual.isEmpty() || novaSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(telaMeuPerfil.this, 
                            "Preencha todos os campos", 
                            "Aviso", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (!senhaAtual.equals(usuarioLogado.getSenha())) {
                    JOptionPane.showMessageDialog(telaMeuPerfil.this, 
                            "Senha atual incorreta", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Atualiza a senha do usuário
                usuarioLogado.setSenha(novaSenha);
                
                // Chama o método editarUsuario para persistir a mudança
                if (Usuario.editarUsuario(usuarioLogado)) {
                    JOptionPane.showMessageDialog(telaMeuPerfil.this, 
                            "Senha alterada com sucesso!\nPor favor, faça login novamente.", 
                            "Sucesso", 
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    // Volta para a tela de login
                    new TelaLogin().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(telaMeuPerfil.this, 
                            "Erro ao alterar senha", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            new TelaMenuPrincipal(usuarioLogado).setVisible(true);
            dispose();
        });
    }
}