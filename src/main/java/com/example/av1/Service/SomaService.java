package com.example.av1.Service;
import com.example.av1.dto.ListarNumerosDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomaService {

    private static final String QUEUE_NAME = "provac2NomeLaura";
    private static final String EXCHANGE_NAME = "senacrmq";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(ListarNumerosDTO listaDTO) {

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, listaDTO);

        String logMessage = "essa lista foi enviada por projetinho A pelo servico para a fila '" + QUEUE_NAME + "': " + listaDTO.getNumeros();
        System.out.println(logMessage);
    }
}