
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.Enums.Basic;
import com.example.demo.Enums.Color;
import com.example.demo.Enums.TipoJugada;
import com.example.demo.Enums.ValoresFicha;
import com.example.demo.entities.Jugada;
import org.springframework.stereotype.Service;

//hacer los cambios acordate que el enum no esta mas y el basic es un jugadaBasic
@Service
public class JugadaService {

    public JugadaService() {

    }

    /**
     * obtiene una jugada en base a un tipo de jugada y un valor de ficha
     *
     * @param jugadaSelect el tipo de jugada a eleccion
     * @param ficha el valor de la ficha
     * @return jugada seteada
     * @throws Exception
     */
    public Jugada getJugada(TipoJugada jugadaSelect, ValoresFicha ficha) throws Exception {

        Jugada jugada = new Jugada();
        switch (jugadaSelect) {
            case PLENO:
                jugada = getPleno();
                break;
            case MEDIO_PLENO:
                jugada = getMedio();
                break;
            case PLENOS_MEDIO:
                jugada = getPlenosMedio();
                break;
            case COMPLEJA:
                jugada = getComplex(10);
                break;
            default:
                throw new Exception("seleccion de jugada errorneo");

        }        
        jugada.setTipoJugada(jugadaSelect);
        calculateScore(jugada);
        if (ficha!=null){
            jugada.setValorFicha(ficha);
            setPay(jugada);            
        }        
        return jugada;
    }

    /**
     * una jugada basica con una postura compuesta de una tipo basico,con un
     * maximo de fichas. no setea el puntaje
     *
     * @param basic constante de clase Jugada un numero que accede al la
     * componente de la posture de Una jugada
     * @param el multiplicador max posible para la jugada
     * @return
     */
    private Jugada getBasic(int basic, int max) {
        int min;
        int multiplier;
        min = 1;
        Jugada j = new Jugada();
        multiplier = (int) (Math.random() * (max - min)) + min;
        j.getPosture()[basic] = multiplier;
        return j;
    }

    /**
     * // testear de nuevo or las dudas añade a una jugada existente un postura
     * en especifico con un maximo de fichas establecido
     *
     * @param basic el tipo basico a agregar
     * @param chip el maximo de fichas a agregar
     * @param j la jugada a la que se le va a anexar el max
     */
    private void addBasic(int basic, int chip, Jugada j) throws Exception {
        if (chip < 1) {
            throw new Exception("no se puede agregar fichas negativas o cero");
        }
        if (basic < 0 || basic > 4) {
            throw new Exception("imposible accder ese basico no existe");
        }
        j.getPosture()[basic] += chip;
    }

    /**
     * retorna una jugada correspondiente a una cantidad de plenos del 0 al 20
     *
     * @return
     */
    private Jugada getPleno() {
        Jugada j = getBasic(Jugada.PLENO, 20);
        return j;
    }

    /**
     * retorna una jugada compuesta de una a 10 medios
     *
     * @return
     */
    private Jugada getMedio() {

        Jugada j = getBasic(Jugada.SEMI, 10);
        return j;
    }

    /**
     * obtiene una jugada compuesta por un pleno del uno al diez y un medio
     *
     * @return
     */
    private Jugada getPlenosMedio() {
        Jugada j;
        j = getBasic(Jugada.PLENO, 10);
        j.getPosture()[Jugada.SEMI]++;
        return j;
    }

    /**
     * se tira una Jugada random con un maximo de fichas
     *
     * @param maxChips la cantidad maxima de fichas que puede tener la jugada
     * @return una Jugada con 1 o mas basics
     */
    private Jugada getComplex(int maxChips) throws Exception {
        Jugada j;
        int chips;
        int basic;
        j = new Jugada();
        if (maxChips < 0) {
            throw new Exception("imposible obtener un complex con ese numero de fichas");
        }
        while (maxChips > 0) {
            //basic = (int) (Math.random() * (Basic.values().length));
            basic=elegirBasicoRandom();
            System.out.println("basico "+basic);//bucle infinito aca cuando crashea
            if (j.getPosture()[basic] == 0) {                
                chips = (int) (Math.random() * (maxChips - 1)) + 1;
                addBasic(basic, chips, j);
                maxChips -= chips;
            }
        }
        return j;
    }

    /**
     * setea el puntaje de una jugada de acuerdo al arreglo de postura
     *
     * @param j la jugada a la que se le va a setear el puntaje
     */
    private void calculateScore(Jugada j) {
        Basic[] basics = Basic.values();
        int[] posture = j.getPosture();
        int sc = 0;
        for (int i = 0; i < j.getPosture().length; i++) {
            sc += posture[i] * basics[i].getPuntaje();
        }
        j.setScore(sc);
    }
    /**
     * setea lo que paga la jugada de acuerdo al valor de ficha asignado
     *
     * @param jugada a la que se le setea el puntaje
     */
    private void setPay(Jugada jugada) {
        Double pay;
        pay = jugada.getValorFicha().getValorNominal() * jugada.getScore();
        jugada.setPay(pay);
    }

    
    /**
     * quiero los plenos y medios y cuadros prevalezcan pro encima de las calles y las lineas
     * @return 
     */
    
    private int elegirBasicoRandom()
    {
        Double selector;
        int i;
        Double  probAcumulada []={0.45,0.70,0.85,0.95,1.0};
        selector=Math.random();
        i=0;
        while(i<probAcumulada.length && selector>probAcumulada[i]){
            i++;
        }
        return i;        
    }
    
}
