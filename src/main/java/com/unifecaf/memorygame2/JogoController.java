/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unifecaf.memorygame2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Gleydson
 */
public class JogoController {

    private JogoView view;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ImageIcon imagemVerso, imagemBlank;
    private Carta primeiraSelecionada;
    private int indexPrimeira, tentativas = 0;
    private boolean aguardando = false;

    public JogoController() {
        carregarRecursos();
        view = new JogoView(6);
        view.criarTabuleiro(36, imagemVerso, e -> tratarClique(Integer.parseInt(e.getActionCommand())));
        view.setVisible(true);
    }

    private void carregarRecursos() {
        try {
            imagemVerso = new ImageIcon(getClass().getResource("/images/back.png"));
            imagemBlank = new ImageIcon(getClass().getResource("/images/blank.png"));
            for (int i = 1; i <= 18; i++) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/foto" + i + ".png"));
                icon.setDescription("f" + i);
                cartas.add(new Carta(icon));
                cartas.add(new Carta(icon));
            }
            Collections.shuffle(cartas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagens de /images/");
            System.exit(0);
        }
    }

    private void tratarClique(int index) {
        if (aguardando) {
            return; // Bloqueia cliques enquanto o timer roda
        }
        Carta carta = cartas.get(index);

        // Evita clicar na mesma carta já virada ou na que acabou de ser clicada
        if (carta.isVirada() || carta.isDescoberta() || carta == primeiraSelecionada) {
            return;
        }

        view.atualizarBotao(index, carta.getImagemFace());
        carta.setVirada(true);

        if (primeiraSelecionada == null) {
            primeiraSelecionada = carta;
            indexPrimeira = index;
        } else {
            tentativas++;

            // Criamos variáveis 'finais' para o Timer não se perder com NullPointer
            final Carta carta1 = primeiraSelecionada;
            final Carta carta2 = carta;
            final int idx1 = indexPrimeira;
            final int idx2 = index;

            if (carta1.getImagemFace().getDescription().equals(carta2.getImagemFace().getDescription())) {
                // ACERTOU
                carta2.setDescoberta(true);
                carta1.setDescoberta(true);
                view.atualizarBotao(idx2, imagemBlank);
                view.atualizarBotao(idx1, imagemBlank);
                view.travarBotao(idx2);
                view.travarBotao(idx1);
                primeiraSelecionada = null; // Limpa para a próxima jogada
                verificarFim();
            } else {
                // ERROU
                aguardando = true;
                Timer timer = new Timer(1000, e -> {
                    view.atualizarBotao(idx2, imagemVerso);
                    view.atualizarBotao(idx1, imagemVerso);
                    carta2.setVirada(false);
                    carta1.setVirada(false);
                    aguardando = false;
                });
                timer.setRepeats(false);
                timer.start();
                primeiraSelecionada = null; // Limpa IMEDIATAMENTE para o próximo par
            }
        }
    }

    private void verificarFim() {
        if (cartas.stream().allMatch(Carta::isDescoberta)) {
            String nome = JOptionPane.showInputDialog(view, "Vitória em " + tentativas + " jogadas! Nome:");
            if (nome != null && !nome.trim().isEmpty()) {
                ScoreDAO.salvarScore(new Score(nome, tentativas));
            }
            view.dispose();
            new MenuController();
        }
    }
}
