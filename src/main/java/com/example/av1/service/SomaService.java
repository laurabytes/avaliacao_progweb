package com.example.av1.service;

import com.example.av1.dto.ListaNumerosDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomaService {

    private static final String QUEUE_NAME = "provac2NomeLaura";
    private static final String EXCHANGE_NAME = "provaciclo2pw1";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(ListaNumerosDTO listaDTO) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, listaDTO);

        String logMessage = "essa lista foi enviada por projetinho A (via Service) para a fila '" + QUEUE_NAME + "': " + listaDTO.getNumeros();
        System.out.println(logMessage);
    }
}