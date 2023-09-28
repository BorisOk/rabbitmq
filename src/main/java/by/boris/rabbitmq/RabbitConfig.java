package by.boris.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации RabbitMQ
 */

@Log4j2
@Configuration
public class RabbitConfig {

    /**
     * Создание соединения.
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    /**
     * Создание админа, для управления обменником.
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * Создание очереди.
     */
    @Bean
    public Queue queue() {
        return new Queue("myQueue");
    }

    /**
     * Создание получателя. Тут указываем соединение, указываем очередь и выводим полученное сообщение в логи.
     * Более простой способ создания получателя. Смотреть класс Consumer.
     */
    /*@Bean
    @Deprecated // old working way of assigning a listener
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("myQueue");
        container.setMessageListener(message -> log.info("[depr] received from myQueue : {}", new String(message.getBody())));
        return container;
    }*/
}
