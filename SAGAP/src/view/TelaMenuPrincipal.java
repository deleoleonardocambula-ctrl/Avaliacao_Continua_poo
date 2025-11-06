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

public class TelaMenuPrincipal extends JFrame {
    
    private Usuario usuarioLogado;
    
    public TelaMenuPrincipal(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        // Configuração básica da janela
        setTitle("Sistema de Gestão - Shigom");
        setSize(600, 400);
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
        titulo.setBounds(200, 20, 250, 30);
        add(titulo);

        // Informações do usuário
        JLabel lblUsuario = new JLabel("Usuário logado: " + usuario.getNome());
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUsuario.setForeground(new Color(70, 70, 70));
        lblUsuario.setBounds(50, 70, 500, 25);
        add(lblUsuario);

        JLabel lblNivelAcesso = new JLabel("Nível de acesso: " + usuario.getNivelDeAcesso());
        lblNivelAcesso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNivelAcesso.setForeground(new Color(70, 70, 70));
        lblNivelAcesso.setBounds(50, 100, 500, 20);
        add(lblNivelAcesso);

        // Botões comuns a todos os usuários
        JButton btnMeuPerfil = new JButton("Meu Perfil");
        btnMeuPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnMeuPerfil.setBounds(50, 150, 200, 35);
        btnMeuPerfil.setBackground(Color.WHITE);
        btnMeuPerfil.setForeground(new Color(70, 70, 70));
        btnMeuPerfil.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        add(btnMeuPerfil);

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSair.setBounds(50, 200, 200, 35);
        btnSair.setBackground(Color.WHITE);
        btnSair.setForeground(new Color(70, 70, 70));
        btnSair.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        add(btnSair);

        // Botões específicos por nível de acesso
        int yPos = 150;
        
        if (usuario.getNivelDeAcesso().equals("Admin")) {
            JButton btnGerenciarUsuarios = new JButton("Gerenciar Usuários");
            btnGerenciarUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnGerenciarUsuarios.setBounds(300, yPos, 200, 35);
            btnGerenciarUsuarios.setBackground(new Color(70, 130, 180));
            btnGerenciarUsuarios.setForeground(Color.WHITE);
            add(btnGerenciarUsuarios);
            yPos += 50;
            
            btnGerenciarUsuarios.addActionListener(e -> {
                new TelaGerirUsuarios(usuarioLogado).setVisible(true);
                dispose();
            });
        }
        
        if (usuario.getNivelDeAcesso().equals("Gestor")) {
            JButton btnGerenciarProdutos = new JButton("Gerenciar Alunos");
            btnGerenciarProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnGerenciarProdutos.setBounds(300, yPos, 200, 35);
            btnGerenciarProdutos.setBackground(new Color(70, 130, 180));
            btnGerenciarProdutos.setForeground(Color.WHITE);
            add(btnGerenciarProdutos);
            yPos += 50;
            
            JButton btnRelatorios = new JButton("Gerenciar Professores");
            btnRelatorios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnRelatorios.setBounds(300, yPos, 200, 35);
            btnRelatorios.setBackground(new Color(70, 130, 180));
            btnRelatorios.setForeground(Color.WHITE);
            add(btnRelatorios);
            yPos += 50;
            
            btnGerenciarProdutos.addActionListener(e -> {
                //TelaGerirProdutos tela = new TelaGerirProdutos(usuarioLogado);
                //tela.setVisible(true);
                dispose();
            });
            
            btnRelatorios.addActionListener(e -> {
                //new TelaRelatorios(usuarioLogado).setVisible(true);
                dispose();
            });
        }
        
        if (usuario.getNivelDeAcesso().equals("Professor")) {
            JButton btnGerirAlunos = new JButton("Realizar Venda");
            btnGerirAlunos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnGerirAlunos.setBounds(300, yPos, 200, 35);
            btnGerirAlunos.setBackground(new Color(70, 130, 180));
            btnGerirAlunos.setForeground(Color.WHITE);
            add(btnGerirAlunos);
            yPos += 50;
            
            btnGerirAlunos.addActionListener(e -> {
                //new TelaRealizarVenda(usuarioLogado).setVisible(true);
                dispose();
            });
        }

        // Ações dos botões
        btnMeuPerfil.addActionListener(e -> {
            new telaMeuPerfil(usuarioLogado).setVisible(true);
            dispose();
        });

        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                this, 
                "Deseja realmente sair do sistema?", 
                "Confirmação", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (resposta == JOptionPane.YES_OPTION) {
                new TelaLogin().setVisible(true);
                dispose();
            }
        });
    }
}