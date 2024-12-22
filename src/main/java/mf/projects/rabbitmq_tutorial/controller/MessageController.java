package mf.projects.rabbitmq_tutorial.controller;

import mf.projects.rabbitmq_tutorial.publisher.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {


    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitmqProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

}
