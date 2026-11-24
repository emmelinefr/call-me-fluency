package dev.alexandraemmeline.call_me_fluency.Infrastructure.WebSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class SessionWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionRegistry webSocketSessionRegistry;

    public SessionWebSocketHandler(WebSocketSessionRegistry webSocketSessionRegistry) {
        this.webSocketSessionRegistry = webSocketSessionRegistry;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String email = (String) session.getAttributes().get("email");

        webSocketSessionRegistry.addSession(email, session);

        log.info("WebSocket connection established for user {}. Session ID: {}", email, session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("WebSocket message received: {}", message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        String email = (String) session.getAttributes().get("email");

        webSocketSessionRegistry.removeSession(email, session);

        log.info("WebSocket connection closed for user {}. Session ID: {}", email, session.getId());
    }


}
