package io.github.tundeadetunji.candidate_service.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.candidate_service.shared.NotificationMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static io.github.tundeadetunji.candidate_service.constants.InlineStrings.CANDIDATE_CREATED;
import static org.mockito.Mockito.*;

class NotificationProducerTest {

    @Test
    void send_shouldPublishMessageToCorrectQueues() throws Exception {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        ObjectMapper mapper = new ObjectMapper();
        NotificationProducer producer = new NotificationProducer(rabbitTemplate, mapper);

        NotificationMessage message = NotificationMessage.builder()
                .subject(CANDIDATE_CREATED)
                .body("Sally Jones has joined.")
                .build();

        String expectedJson = mapper.writeValueAsString(message);

        producer.send(message);

        verify(rabbitTemplate).convertAndSend("app.exchange", "recruiter.notify", expectedJson);
    }
}
