package mf.projects.rabbitmq_tutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.topic.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing_key}")
    private String routingKey;
    // spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }
//    Biding between exchange and the queue using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
