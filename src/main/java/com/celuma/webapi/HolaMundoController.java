package com.celuma.webapi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HolaMundoController {

    @RequestMapping("/hola")
    public String saludar() {
        return "Nunca pares de aprender";
    }
}
