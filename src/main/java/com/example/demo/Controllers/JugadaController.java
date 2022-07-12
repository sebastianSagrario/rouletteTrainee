package com.example.demo.Controllers;

import com.example.demo.Enums.TipoJugada;
import com.example.demo.Enums.ValoresFicha;
import com.example.demo.entities.Jugada;
import com.example.demo.services.JugadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugada")
public class JugadaController {

    JugadaService jugadaService;

    @Autowired
    public JugadaController(JugadaService jugadaService) {
        this.jugadaService = jugadaService;
    }

    /**
     * deplega el menu de seleccion de la jugada a la vista el usuario elije el
     * tipo de jugada y con que ficha le va a salir
     *
     * @param mm
     * @return
     */
    @GetMapping("/seleccion")
    public String seleccionJugada(ModelMap mm) {
        mm.addAttribute("TiposJugadas", TipoJugada.values());
        mm.addAttribute("valoresFicha", ValoresFicha.values());
        return "seleccionJugada";
    }

    /**
     * envia a la vista una jugada para practicar posturas
     *
     * @param jugadaSelect el tipo de jugada en cuestion a practicar
     * @param fichas valor unitario de la ficha
     * @param mm model que envia la jugada
     * @return la vista con el form para practique el aspirante
     */
    @GetMapping("/practicaJugada")
    public String practicaPostura(@RequestParam TipoJugada jugadaSelect, @RequestParam(required = false) ValoresFicha fichas, ModelMap mm) {
        try {
            Jugada j;
            System.out.println(jugadaSelect + "-----" + fichas);
            j = jugadaService.getJugada(jugadaSelect, fichas);
            mm.addAttribute("jugada", j);
            mm.addAttribute("tipoJugada", jugadaSelect);
            mm.addAttribute("fichas", fichas);
        } catch (Exception e) {
            System.out.println("erororrrr");
            System.out.println(e.getMessage());
            mm.addAttribute("error", e.getMessage());
        }
        return "practicaPostura";
    }

    /**
     *
     * recibe el puntaje de un jugada desde un formulario y califica si la
     * respuesta es correcta, si es correcta y se solicito un monto se pide un
     * corte, sino sigue pidiendo postura con putnaje simple
     *
     * @param score el puntaje de la jugada
     * @param tipoJugada el tipo de jugada que se esta evaluando
     * @param response la respuesta que otorga el aspirante
     * @param rd
     * @return si acierta otro formulario para que siga jugando, sino una vista
     * que diga que perdio
     */
    @PostMapping("/calificaJugada")
    public String calificaPracticaJugada(@RequestParam Double score, @RequestParam TipoJugada tipoJugada, @RequestParam ValoresFicha fichas, @RequestParam int response, RedirectAttributes rd) {

        System.out.println("funciona mierda!");
        try{
            String dir;
            if (response != score) {
                return "fail";
            }
            if (fichas == null) {
                rd.addFlashAttribute("exito", true);
                return "redirect:/jugada/practicaJugada?jugadaSelect=" + tipoJugada;
            }
            return "redirect:/jugada/corte?fichas=" + fichas + "&monto=" + score;            
        }
        catch(Exception e){
            return "redirect:/jugada/seleccion";
        }
    }

    @GetMapping("/seleccionCorte")
    public String solicitarCorte(ModelMap mm)
    {
        mm.addAttribute("valoresFicha", ValoresFicha.values());
        return "corteSeleccion";
    }
    
    
    /**
     * desplega el formulario para que el aspirante haga un corte
     *
     * @param fichas con las fichas que se va ahacer el corte
     * @param monto con el monto que se va tratar
     * @param mm
     * @return
     */
    @GetMapping("/corte")
    public String GetCorte(@RequestParam ValoresFicha fichas, @RequestParam Double monto, ModelMap mm) {
        if(monto%fichas.getValorNominal()!=0){

            mm.addAttribute("error","no es posible obtener ese monto con esas fichas");
            return "redirect:/jugada/seleccionCorte";
        }
        mm.addAttribute("valorfichas", fichas);
        mm.addAttribute("monto", monto);
        return "corte";
    }

    /**
     * evalua un corto, si se hizo correctamente o no
     *
     * @param monto el monto a pagar
     * @param cantFichas la cantidad de fichas que se entregan
     * @param valorFicha el valor nominal de las fichas con las que se pagan
     * @param complemento si hubiese
     * @param rd
     * @return
     */
    @PostMapping("/evaluaCorte")
    public String evaluaCorte(
            @RequestParam Double monto,
            @RequestParam Double complemento,
            @RequestParam Integer cantFichas,
            @RequestParam ValoresFicha valorFicha,
            RedirectAttributes rd) {
        //falta validar aca
        if (monto != (complemento + cantFichas * valorFicha.getValorNominal())) {
            return "fail";
        }
        rd.addFlashAttribute("exito", true);
        return "redirect:/";
    }
}
