package by.boris.rabbitmq.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Класс слушателя.
 */

@Log4j2
@EnableRabbit
@Component
public class Consumer {

    @RabbitListener(queues = "myQueue1")
    public void processMyQueue(String message) {
        log.info("received - [1] from (myQueue1) : {}", message);
    }

    @RabbitListener(queues = "myQueue2")
    public void processMyQueueTwo(String message) {
        log.info("received - [2] from (myQueue2) : {}", message);
    }
}
