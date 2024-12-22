package mf.projects.rabbitmq_tutorial.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitmqConsumer {


    private static final Logger LOGGER = Logger.getLogger(RabbitmqConsumer.class.getName());

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){
        LOGGER.info(String.format("Message received <- %s", message));
    }



}
