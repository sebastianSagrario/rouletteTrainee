/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.entities.Jugada;
import com.example.demo.services.JugadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//replantear todos los controladores

@Controller
@RequestMapping("/jugada")
public class JugadaController {

    JugadaService jugadaService;

    @Autowired
    public JugadaController(JugadaService jugadaService) {
        this.jugadaService = jugadaService;
    }

    @GetMapping("/practicaPlenos")
    public String practicaPlenos(ModelMap mm) {
        mm.addAttribute("jugada", jugadaService.getPleno());
        return "practicaPostura";
    }

    @GetMapping("/practicaMedios")
    public String practicaMedioPlenos(ModelMap mm) {
        mm.addAttribute("jugada", jugadaService.getMedio());
        return "practicaPostura";
    }

    @GetMapping("/practicaPlenosMedios")
    public String practicaPlenosMedio(ModelMap mm) {
        mm.addAttribute("jugada", jugadaService.getPlenosMedio());
        return "practicaPostura";
    }

    @GetMapping("/practicaJugadaMixta")
    public String practicaMixta(ModelMap mm) {
        mm.addAttribute("jugada", jugadaService.getComplex(5));        
        return "practicaPostura";
    }

    @PostMapping("/calificaJugada")
    public String calificaPracticaPlenos(@RequestParam int score ,@RequestParam int response) {
        
        if (response != score) {
            return "fail";            
        }
        return "success";
    }
}
