
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.Enums.Basic;
import com.example.demo.entities.Jugada;
import org.springframework.stereotype.Service;

//hacer los cambios acordate que el enum no esta mas y el basic es un jugadaBasic
@Service
public class JugadaService {

    public JugadaService()
    {
        
    }
    
    /**
     * una jugada basica con una postura compuesta de una tipo basico,con un maximo de fichas. no setea el puntaje 
     * @param basic constante de clase Jugada un numero que accede al la componente de la posture de Una jugada
     * @param el multiplicador max posible para la jugada
     * @return 
     */
    public Jugada getBasic(int basic,int max)
    {
        int min;
        int multiplier;        
        min=0;
        Jugada j=new Jugada();
        multiplier = (int) (Math.random() * (max - min)) + min;
        j.getPosture()[basic]=multiplier;        
        return j;
    }
    
    
    /**
     * // testear de nuevo or las dudas
     * añade a una jugada existente un postura en especifico con un maximo de fichas establecido 
     * @param basic el tipo basico a agregar
     * @param chip el maximo de fichas a agregar
     * @param j la jugada a la que se le va a anexar el max
     */
    public void addBasic(int basic,int chip,Jugada j)
    {        
        j.getPosture()[basic]+=chip;                
    }
    
    /**
     * retorna una jugada correspondiente a una cantidad de plenos del 0 al 20
     * @return 
     */
    public Jugada getPleno()
    {
        Jugada j=getBasic(Jugada.PLENO,20);
        calculateScore(j);        
        return j;
    }
    
    /**
     * retorna una jugada compuesta de una a 10 medios
     * @return 
     */
    public Jugada getMedio()
    {
        
        Jugada j=getBasic(Jugada.SEMI,10);
        calculateScore(j);        
        return j;        
    }
    
    /**
     * obtiene una jugada compuesta por un pleno del uno al diez y un medio 
     * @return 
     */
    public Jugada getPlenosMedio()
    {
        Jugada j;
        j=getBasic(Jugada.PLENO,10);
        j.getPosture()[Jugada.SEMI]++;
        calculateScore(j);
        for (int p : j.getPosture())
        {
            System.out.println(p); 
        }
        return j;
    }
    
    /**
     * se tira una Jugada random con un maximo de fichas
     * @param maxChips la cantidad maxima de fichas que puede tener la jugada
     * @return una Jugada con 1 o mas basics 
     */
    public Jugada getComplex(int maxChips)
    {
        Jugada j;
        j=new Jugada();
        int chips; 
        int basic;
        while (maxChips>0)
        {            
            System.out.println("maxChips"+maxChips);
           basic=(int) (Math.random() * (Basic.values().length));
           if (j.getPosture()[basic]==0)
           {
               System.out.println("basic"+basic);
               chips = (int) (Math.random() * (maxChips - 1)) + 1;
               System.out.println("chips " +chips);
               addBasic(basic,chips,j);            
               maxChips-=chips;
           }
        }        
        calculateScore(j);
        return j;
    }

    /**
     * setea el puntaje de una jugada de acuerdo al arreglo de postura
     * @param j la jugada a la que se le va a setear el puntaje 
     */
    private void calculateScore(Jugada j) {
        Basic [] basics=Basic.values();
        int [] posture=j.getPosture();
        int sc=0;
        for (int i=0;i<j.getPosture().length;i++)
        {
            sc+= posture[i] * basics[i].getPuntaje();            
        }
        
        j.setScore(sc);                
    }
    
    
}
