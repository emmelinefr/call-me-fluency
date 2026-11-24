package dev.alexandraemmeline.call_me_fluency.Infrastructure.WebSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionRegistry {

    private final ConcurrentHashMap<String, Set<WebSocketSession>> sessions = new ConcurrentHashMap<>();

    public void addSession(String email, WebSocketSession session) {
        sessions.compute(email, (key, userSessions) -> {

            if (userSessions == null) {
                userSessions = ConcurrentHashMap.newKeySet();
            }

            userSessions.add(session);

            return userSessions;

        });
    }

    public void removeSession(String email, WebSocketSession session) {
        sessions.computeIfPresent(email, (key, userSessions) -> {

            userSessions.remove(session);

            if (userSessions.isEmpty()) {
                return null;
            }

            return userSessions;

        });

    }

}
