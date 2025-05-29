package com.app.impressao.handler;

import com.app.impressao.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SessionHandler extends StompSessionHandlerAdapter {

    @Autowired
    private PrintService printService;

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        byte[] content = (byte[]) payload;

        printService.print(content);
    }
}
