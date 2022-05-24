/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.example.demo.Enums;

/**
 *
 * @author Toshi-Ba
 */
public enum Basic {

    PLENO(35), MEDIO(17), CUADRO(8), CALLE(11), LINEA(5);
    int puntaje;

    private Basic(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
