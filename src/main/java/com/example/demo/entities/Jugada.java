/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entities;

import com.example.demo.Enums.Basic;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jugada {

    @Id
    private String id;
    private int score;
    private int[] posture;//contador de basicos
    public static final int PLENO = 0;
    public static final int SEMI = 1;
    public static final int CUADRO = 2;
    public static final int CALLE = 3;
    public static final int LINEA = 4;

    public Jugada() {
        posture = new int[Basic.values().length];
        score = 0;
    }

    public Jugada(int score, int[] posture) {
        this.score = score;
        this.posture = posture;
    }

    public int getScore() {
        return score;
    }

    public int[] getPosture() {
        return posture;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPosture(int[] posture) {
        this.posture = posture;
    }

    @Override
    public String toString() {
        String jugada = "";
        Basic[] basicos;
        basicos = Basic.values();
        for (int i = 0; i < posture.length; i++) {
            if (posture[i] != 0) {
                jugada += posture[i] + " " + basicos[i]+" ";                
            }
        }
        return jugada;

    }

    public String getId() {
        return id;
    }

    public static int getPLENO() {
        return PLENO;
    }

    public static int getSEMI() {
        return SEMI;
    }

    public static int getCUADRO() {
        return CUADRO;
    }

    public static int getCALLE() {
        return CALLE;
    }

    public static int getLINEA() {
        return LINEA;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}
