package com.juanlopezaranzazu.hello_world.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @GetMapping("/")
    public String getMessages(){
        return "Obtener mensajes";
    }

    @GetMapping("/{id}")
    public String getMessageById(@PathVariable String id){
        return "Obtener mensaje " + id;
    }

    @PostMapping("/")
    public String createMessage(){
        return "Crear mensaje";
    }

    @PutMapping("/{id}")
    public String updateMessage(@PathVariable String id){
        return "Actualizar mensaje " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable String id){
        return "Eliminar mensaje " + id;
    }
}
