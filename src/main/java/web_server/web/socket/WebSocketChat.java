package web_server.web.socket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint("/socket/chat")
//client 접속 시 생성, client와 직접 통신
//session 관련 정보를 정적 저장, 1 대 다 통신이 가능
public class WebSocketChat {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    //연결이 이미 되었으면 세션을 생성하지 않음
    @OnOpen
    public void onOpen(Session session) throws Exception {
        if(!clients.contains(session)) {
            clients.add(session);
        }else  {
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for (Session client : clients) {
            client.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }
}
