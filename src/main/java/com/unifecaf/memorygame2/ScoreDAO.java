/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unifecaf.memorygame2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Gleydson
 */
public class ScoreDAO {
    private static final String ARQUIVO = "recordes.ser";

    public static void salvarScore(Score novoScore) {
        ArrayList<Score> scores = lerScores();
        scores.add(novoScore);
        scores.sort(Comparator.comparingInt(Score::getTentativas));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Score> lerScores() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (ArrayList<Score>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
