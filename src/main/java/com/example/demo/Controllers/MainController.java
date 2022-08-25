package com.example.demo.Controllers;

import com.example.demo.Enums.TipoJugada;
import com.example.demo.Enums.ValoresFicha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home(ModelMap mm) {
                mm.addAttribute("TiposJugadas", TipoJugada.values());
        mm.addAttribute("valoresFicha", ValoresFicha.values());
        return "index";
    }
}
