/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unifecaf.memorygame2;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleydson
 */
public class MenuView extends JFrame {
    private JButton btnNovoJogo = new JButton("Novo Jogo");
    private JButton btnRecordes = new JButton("Recordes");
    private JButton btnSair = new JButton("Sair");

    public MenuView() {
        setTitle("Menu Principal - Jogo da Memória");
        setSize(400, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());

        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 10, 25));
        Dimension dim = new Dimension(220, 60);
        
        btnNovoJogo.setPreferredSize(dim);
        btnRecordes.setPreferredSize(dim);
        btnSair.setPreferredSize(dim);

        painelBotoes.add(btnNovoJogo);
        painelBotoes.add(btnRecordes);
        painelBotoes.add(btnSair);

        add(painelBotoes);
        setLocationRelativeTo(null);
    }

    public JButton getBtnNovoJogo() { return btnNovoJogo; }
    public JButton getBtnRecordes() { return btnRecordes; }
    public JButton getBtnSair() { return btnSair; }
}
