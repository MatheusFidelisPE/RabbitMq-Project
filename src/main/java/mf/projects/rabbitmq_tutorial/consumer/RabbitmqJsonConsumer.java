package mf.projects.rabbitmq_tutorial.consumer;

import mf.projects.rabbitmq_tutorial.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RabbitmqJsonConsumer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitmqJsonConsumer.class);

//    O modo automático de ack também funciona bem. Quando uma mensagem não é processada com sucesso, ela é reenviada para a fila e processada para que seja processada novamente.
    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"}, ackMode = "AUTO")
    public void receiveMessage(User user)  {
        double random = Math.random();
        LOGGER.info(String.format("User %s %s with id %d was received", user.getFirstName(), user.getLastName(), user.getId()));
        if (random <= 0.2){
            throw new RuntimeException("Error processing message");
        }

    }


}
