package by.boris.rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Класс используется в качестве издателя.
 */

@Log4j2
@Service
@AllArgsConstructor
public class ProducerImpl implements Producer {
    private final AmqpTemplate template;

    /**
     * <p>Отправляем каждое сообщение 10 раз, для отличия добавили UUID.
     * Что б по логам получателей посмотреть как будут браться сообщения.
     * И мы увидем что получают сообщения слушатели, "рандомно" то есть кто был
     * свободен в этот момент, тот и забрал его.</p>
     */
    @Override
    public void convertAndSend(String message) {
        for (int i = 0; i < 10; i++) {
            template.convertAndSend("myQueue", message + " uuid = " + UUID.randomUUID());
        }
    }
}
