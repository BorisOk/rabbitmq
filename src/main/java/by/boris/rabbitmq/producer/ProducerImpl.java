package by.boris.rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Класс используется в качестве издателя.
 */

@Log4j2
@Service
@AllArgsConstructor
public class ProducerImpl implements Producer {
    private final RabbitTemplate template;

    /**
     * Отправляем сообщения по обменнику, без указания очереди.
     * В какие очереди пойдет сообщение решает обменник.
     * Смотреть в классе RabbitConfig.
     *
     * И все очереди гарантированно получат это сообщение.
     */
    @Override
    public void convertAndSend(String message) {
        template.setExchange("exchange");
        template.convertAndSend(message);
    }
}
