
# RabbitMQ Project

Este projeto demonstra o uso do **RabbitMQ** para mensageria entre microserviços utilizando **Spring Boot**. Ele possui uma aplicação que envia mensagens em dois formatos diferentes: **String** e **JSON** (representado por um objeto `User`). As mensagens são enviadas para filas diferentes, e o projeto utiliza a arquitetura baseada em **Exchange**, **Queue** e **Binding** para garantir a entrega das mensagens.

## Funcionalidades

- **Envio de mensagens em formato String**: Envia mensagens simples (strings) para uma fila específica.
- **Envio de mensagens em formato JSON**: Envia objetos `User` serializados como JSON para uma fila diferente.
- **Configuração com RabbitMQ**: Utiliza RabbitMQ para orquestrar a troca de mensagens entre produtores e consumidores.
  
## Estrutura do Projeto

- **RabbitmqProducer**: Envia mensagens do tipo String para a fila configurada.
- **RabbitmqJsonProducer**: Envia objetos `User` como JSON para a fila configurada.
- **RabbitmqConfig**: Configuração do RabbitMQ, incluindo Exchange, Fila, Binding e o conversor de mensagens (para converter objetos Java em JSON).
- **Controladores**: Exposição de endpoints HTTP para que você possa enviar as mensagens via API:
  - `POST /publish`: Envia uma mensagem do tipo String.
  - `POST /json_publish`: Envia uma mensagem JSON com um objeto `User`.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento do backend.
- **RabbitMQ**: Sistema de mensageria para comunicação entre microserviços.
- **Spring AMQP**: Biblioteca Spring para integração com RabbitMQ.
- **Jackson**: Biblioteca para conversão de objetos Java para JSON.
  
## Como Rodar o Projeto

### Pré-requisitos

- **Java 21 ou superior**
- **RabbitMQ** instalado ou utilização do RabbitMQ Cloud (caso deseje rodar localmente, você pode usar o Docker para levantar o serviço do RabbitMQ).

### Passos para execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/MatheusFidelisPE/RabbitMq-Project.git
   ```

2. **Instale as dependências do projeto**:
   Caso esteja usando **Maven**, rode:
   ```bash
   mvn clean install
   ```

3. **Configure o RabbitMQ**:
   - Caso esteja rodando localmente, certifique-se de que o RabbitMQ esteja em execução na URL `localhost:5672`.
   - Caso esteja utilizando um serviço na nuvem, configure as variáveis no arquivo `application.properties`.

4. **Execute a aplicação**:
   Para iniciar o servidor Spring Boot, rode:
   ```bash
   mvn spring-boot:run
   ```

5. **Teste os Endpoints**:
   - **Enviar String**:
     Use o endpoint `GET` para enviar uma mensagem simples:
     ```bash
     curl "http://localhost:8080/publish?message=HelloRabbitMQ"
     ```

   - **Enviar JSON**:
     Use o endpoint `POST` para enviar um objeto `User` como JSON:
     ```bash
     curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","email":"johndoe@example.com"}' http://localhost:8080/json_publish
     ```

## Configuração do RabbitMQ

O arquivo `application.properties` contém as configurações do RabbitMQ, incluindo a **Exchange**, **Routing Key** e **Queue**.

Exemplo de configurações:
```properties
spring.rabbitmq.host=<>
spring.rabbitmq.port=<>
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

rabbitmq.topic.exchange.name=<your_exchange_name>
rabbitmq.queue.name=<your_queue_name>
rabbitmq.routing_key=<your_routing_key_name_to_the_first_queue>
rabbitmq.json.queue.name=<your_queue_name>
rabbitmq.json.routing_key=<your_routing_key_name_to_the_second_queue>
```

