package mf.projects.rabbitmq_tutorial.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.topic.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing_key}")
    private String routingKey;
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.json.routing_key}")
    private String jsonRoutingKey;
    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueueName;


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    public Queue jsonQeue() {
        return new Queue(jsonQueueName);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQeue())
                .to(topicExchange())
                .with(jsonRoutingKey);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
