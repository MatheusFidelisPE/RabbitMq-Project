package mf.projects.rabbitmq_tutorial.publisher;

import mf.projects.rabbitmq_tutorial.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitmqJsonProducer {

    @Value("${rabbitmq.topic.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.json.routing_key}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = Logger.getLogger(RabbitmqJsonProducer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user) {
        for (int i = 0; i < 10; i++) {
            user.setId(user.getId()+i);
            rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, user);
            LOGGER.info(String.format("Message sent -> %s", user.toString()));
        }
    }
}
