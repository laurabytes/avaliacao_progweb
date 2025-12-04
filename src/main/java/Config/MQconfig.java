package Config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

    @Component
    public class MQConfig {

        private static final String QUEUE_NAME = "provac2NomeLaurinha";
        private static final String EXCHANGE_NAME = "senacrmq";

        @Autowired
        private AmqpAdmin amqpAdmin;
        private Queue queue;

        private DirectExchange createDirectExchange(){
            return new DirectExchange(EXCHANGE_NAME);
        }

        @PostConstruct
        private void Create (){
            this.queue = new Queue(QUEUE_NAME, true, false, false);
            DirectExchange directExchange = createDirectExchange();

            Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE,
                    directExchange.getName(), queue.getName(), null);

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(directExchange);
            amqpAdmin.declareBinding(binding);
            System.out.println("PROJETO A: Fila '" + QUEUE_NAME + "' declarada com sucesso no RabbitMQ.");
        }

        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                             MessageConverter jsonMessageConverter) {
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setMessageConverter(jsonMessageConverter);
            return template;
        }

        @Bean
        public MessageConverter jsonMessageConverter() {
            return new Jackson2JsonMessageConverter();
        }
    }

