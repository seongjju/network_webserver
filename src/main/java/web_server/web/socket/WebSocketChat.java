package web_server.web.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint("/socket/chat")
//client 접속 시 생성, client 와 직접 통신
//session 관련 정보를 정적 저장, 1 대 다 통신이 가능
public class WebSocketChat {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); //동기화된 세션 저장소

    //연결이 이미 되었으면 세션을 생성하지 않음
    @OnOpen
    public void onOpen(Session session) throws Exception {
        if(!clients.contains(session)) {
            clients.add(session); //새로운 클라이언트 세션 추가
        } else  {
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for (Session client : clients) {
            client.getBasicRemote().sendText(message); //모든 클라이언트에 메시지 전송
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session); //클라이언트 세션종료
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }
}
