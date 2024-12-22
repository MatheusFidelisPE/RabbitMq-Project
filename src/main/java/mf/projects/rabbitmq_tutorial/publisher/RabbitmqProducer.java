package mf.projects.rabbitmq_tutorial.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitmqProducer {

    @Value("${rabbitmq.routing_key}")
    private String routingKey;
    @Value("${rabbitmq.topic.exchange.name}")
    private String exchangeName;


    private static final Logger LOGGER = Logger.getLogger(RabbitmqProducer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);

    }





}
