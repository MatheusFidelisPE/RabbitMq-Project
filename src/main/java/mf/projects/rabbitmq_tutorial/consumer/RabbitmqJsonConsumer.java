package mf.projects.rabbitmq_tutorial.consumer;

import mf.projects.rabbitmq_tutorial.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqJsonConsumer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitmqJsonConsumer.class);


    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void receiveMessage(User user) {
        LOGGER.info("Received message <- " + user.toString());
    }


}
