package com.example.av1.controller;
import com.example.av1.dto.ListaNumerosDTO;
import com.example.av1.service.SomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lista")
public class SomaController {

    @Autowired
    private SomaService somaService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarLista(@RequestBody ListaNumerosDTO listaDTO){


        somaService.enviarMensagem(listaDTO);

        return ResponseEntity.ok("msg enviada com sucesso: " + listaDTO.getNumeros());
    }
}