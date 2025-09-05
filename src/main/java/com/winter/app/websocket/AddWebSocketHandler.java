package com.winter.app.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddWebSocketHandler implements WebSocketHandler {
	
	private List<WebSocketSession> users = new ArrayList<>();
	private Map<String, WebSocketSession> map = new HashMap<>();
	
	// 웹소켓으로 연결되었을 때
	// WebSocketSession 톰캣이 만들어주는 세션이랑은 다른 것임
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		map.put(authentication.getName(), session);
		
		log.warn("{}", session);
		users.add(session);
	}

	// 사용자가 메시지를 전송했을 때
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		log.warn("{}", message.getPayload());
		users.forEach(u -> {
			try {
				u.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// 웹소켓 연결이 끊겼을 때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
	}
	
	// 대용량 문서 관련
	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
