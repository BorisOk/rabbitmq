package by.boris.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
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
     * Создание очереди № 1
     */
    @Bean
    public Queue queueOne() {
        return new Queue("myQueue1");
    }

    /**
     * Создание очереди № 2
     */
    @Bean
    public Queue queueTwo() {
        return new Queue("myQueue2");
    }

    /**
     * Создание обменник
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topic-exchange");
    }

    /**
     * Создание связку между очередями
     * Вместо ключа (key) указывается регулярное выражение (one.*) с которым идет сообщение. Для очереди (myQueue1)
     */
    @Bean
    public Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(exchange()).with("one.*");
    }

    /**
     * Создание связку между очередями
     * Вместо ключа (key) указывается регулярное выражение (*.two) с которым идет сообщение. Для очереди (myQueue2)
     */
    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(exchange()).with("*.two");
    }

}
