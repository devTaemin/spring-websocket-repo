package lab.malangdemo.domain.message.api;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import lab.malangdemo.domain.message.dto.Message;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations messageSendingOperations;

    /*
        /app/message            메시지 발행
        /topic/channelId        구독
     */

    @MessageMapping("/message")
    public void newUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("username", message.getSender());
        messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);
    }
}
