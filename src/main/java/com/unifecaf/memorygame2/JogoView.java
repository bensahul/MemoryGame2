package com.unifecaf.memorygame2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Gleydson
 */
public class JogoView extends JFrame {
    private ArrayList<JButton> botoes = new ArrayList<>();
    private final int TAM_ICONE = 80;

    public JogoView(int colunas) {
        setTitle("Jogo da Memória - 6x6");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(colunas, colunas, 5, 5));
    }

    public void criarTabuleiro(int quantidade, ImageIcon verso, java.awt.event.ActionListener listener) {
        for (int i = 0; i < quantidade; i++) {
            JButton btn = new JButton(redimensionar(verso));
            btn.setPreferredSize(new Dimension(TAM_ICONE + 10, TAM_ICONE + 10));
            btn.setActionCommand(String.valueOf(i));
            btn.addActionListener(listener);
            botoes.add(btn);
            add(btn);
        }
        pack();
        setLocationRelativeTo(null);
    }

    public void atualizarBotao(int index, ImageIcon img) {
        botoes.get(index).setIcon(redimensionar(img));
    }

    public void travarBotao(int index) {
        botoes.get(index).setEnabled(false);
        botoes.get(index).setBorderPainted(false);
    }

    private ImageIcon redimensionar(ImageIcon icon) {
        if (icon == null) return null;
        Image img = icon.getImage().getScaledInstance(TAM_ICONE, TAM_ICONE, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
