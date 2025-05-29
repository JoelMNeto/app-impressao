package com.app.impressao;

import com.app.impressao.factory.StompClientFactory;
import com.app.impressao.handler.SessionHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootApplication
public class ImpressaoApplication {

	public static final String URL = "https://localhost:8080/ws";

	public static void main(String[] args) {
		WebSocketStompClient stompClient = StompClientFactory.getStompClient();

		stompClient.connectAsync(URL, new SessionHandler());
	}
}
