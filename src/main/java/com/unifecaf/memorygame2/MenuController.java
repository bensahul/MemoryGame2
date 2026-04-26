package com.unifecaf.memorygame2;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Gleydson
 */
public class MenuController {
    private MenuView menuView;

    public MenuController() {
        menuView = new MenuView();
        
        menuView.getBtnNovoJogo().addActionListener(e -> {
            new JogoController();
            menuView.dispose();
        });

        menuView.getBtnRecordes().addActionListener(e -> exibirRecordes());

        menuView.getBtnSair().addActionListener(e -> confirmarSaida());

        menuView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) { confirmarSaida(); }
        });

        menuView.setVisible(true);
    }

    private void confirmarSaida() {
        int opcao = JOptionPane.showConfirmDialog(menuView, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) System.exit(0);
    }

    private void exibirRecordes() {
        ArrayList<Score> lista = ScoreDAO.lerScores();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(menuView, "Nenhum recorde ainda!");
            return;
        }
        StringBuilder sb = new StringBuilder("🏆 RECORDES 🏆\n\n");
        for (int i = 0; i < Math.min(lista.size(), 5); i++) {
            sb.append((i + 1)).append("º: ").append(lista.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(menuView, sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuController());
    }
}
