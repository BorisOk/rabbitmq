package by.boris.rabbitmq.controller;

import by.boris.rabbitmq.producer.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@AllArgsConstructor
@RestController
public class SampleController {
    private final Producer producer;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody String message) {
        log.info("Send to myQueue");
        producer.convertAndSend(message);
        return ResponseEntity.ok("Success send to myQueue");
    }
}
