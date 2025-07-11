package io.github.tundeadetunji.candidate_service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.tundeadetunji.candidate_service.config.RabbitMqConfig;
import io.github.tundeadetunji.candidate_service.shared.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static io.github.tundeadetunji.candidate_service.config.RabbitMqConfig.EXCHANGE;
import static io.github.tundeadetunji.candidate_service.config.RabbitMqConfig.ROUTING_KEY;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "app.exchange";
    private static final String ROUTING_KEY = "recruiter.notify";
    private final ObjectMapper objectMapper;

    @Retry(name = "recruiterNotify", fallbackMethod = "fallbackSend")
    @RateLimiter(name = "recruiterNotify", fallbackMethod = "fallbackSend")
    @CircuitBreaker(name = "recruiterNotify", fallbackMethod = "fallbackSend")
    public void send(NotificationMessage message) {
        try {
            String json = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, json);
        } catch (JsonProcessingException e) {
            //in production, log this
        }
    }

    public void fallbackSend(NotificationMessage message, Throwable t) {
        // in prod, optionally log to file or store in DB for retry queue
    }

}
