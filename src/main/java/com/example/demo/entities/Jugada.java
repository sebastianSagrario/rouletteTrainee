package com.example.demo.entities;

import com.example.demo.Enums.Basic;
import com.example.demo.Enums.Color;
import com.example.demo.Enums.TipoJugada;
import com.example.demo.Enums.ValoresFicha;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Jugada {

    @Id
    private String id;
    private int score;
    private int[] posture;//contador de basicos
    private Color color;
    private Integer number;
    private TipoJugada tipoJugada;
    private ValoresFicha valorFicha;
    private Double pay;
    public static final int PLENO = 0;
    public static final int SEMI = 1;
    public static final int CUADRO = 2;
    public static final int CALLE = 3;
    public static final int LINEA = 4;

    public Jugada() {
        posture = new int[Basic.values().length];
        score = 0;
        number=(int)(Math.random()*36);
    }

    public Jugada(int score, int[] posture) {
        this.score = score;
        this.posture = posture;
    }
    
    @Override
    public String toString() {
        String jugada = "";
        Basic[] basicos;
        basicos = Basic.values();
        for (int i = 0; i < posture.length; i++) {
            if (posture[i] != 0) {
                jugada += posture[i] + " " + basicos[i] + " ";
            }
        }
        return jugada;

    }
}
