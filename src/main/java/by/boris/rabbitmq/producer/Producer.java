package by.boris.rabbitmq.producer;

public interface Producer {
    void convertAndSend(String message);
}
