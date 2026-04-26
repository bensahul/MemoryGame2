package com.unifecaf.memorygame2;

import java.io.Serializable;

/**
 * @author Gleydson
 */
public class Score implements Serializable {
    private String nome;
    private int tentativas;

    public Score(String nome, int tentativas) {
        this.nome = nome;
        this.tentativas = tentativas;
    }

    public int getTentativas() { return tentativas; }
    
    @Override
    public String toString() {
        return nome + " - " + tentativas + " tentativas";
    }
}
