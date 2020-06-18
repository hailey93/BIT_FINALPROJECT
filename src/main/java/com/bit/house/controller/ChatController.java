package com.bit.house.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    /*private final RedisPublisher redisPublisher;
    private final ChatService chatService;*/

    @GetMapping("/chat")
    public String startChat(){
        return "th/main/chatting";
    }
}
