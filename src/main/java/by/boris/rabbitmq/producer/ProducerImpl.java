package by.boris.rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * Класс используется в качестве издателя.
 */

@Log4j2
@Service
@AllArgsConstructor
public class ProducerImpl implements Producer {
    private final AmqpTemplate template;

    @Override
    public void convertAndSend(String message) {
        template.convertAndSend("myQueue", message);
    }
}
