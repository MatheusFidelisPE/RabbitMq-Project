package mf.projects.rabbitmq_tutorial.controller;

import mf.projects.rabbitmq_tutorial.dto.User;
import mf.projects.rabbitmq_tutorial.publisher.RabbitmqJsonProducer;
import mf.projects.rabbitmq_tutorial.publisher.RabbitmqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {


    @Autowired
    private RabbitmqProducer rabbitmqProducer;
    @Autowired
    private RabbitmqJsonProducer rabbitmqJsonProducer;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitmqProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/json_publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitmqJsonProducer.sendJsonMessage(user);

        return ResponseEntity.ok("User sent successfully");

    }


}
