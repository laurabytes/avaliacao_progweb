package Controller;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lista")
public class SomaController {

    private static final String QUEUE_NAME = "provac2NomeLaura";
    private static final String EXCHANGE_NAME = "senacrmq";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarLista(@RequestBody ListaNumerosDTO listaDTO){


        rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, listaDTO);

        String logMessage = "Lista enviada por PROJETO A para a fila '" + QUEUE_NAME + "': " + listaDTO.getNumeros();
        System.out.println(logMessage);

        return ResponseEntity.ok("Mensagem enviada com sucesso: " + listaDTO.getNumeros());
    }
}
