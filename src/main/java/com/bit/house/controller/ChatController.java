package com.bit.house.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    /*private final RedisPublisher redisPublisher;
    private final ChatRepository chatRepository;

    @MessageMapping("/chat/message")
    public void message(ChatVO message){
        if(ChatVO.MessageType.ENTER.equals(message.getType())){
            chatRepository.enterChatRoom(message.getChatId());
            message.setMsg(message.getSender()+"님이 입장하셨습니다.");
        }
        redisPublisher.publish(chatRepository.getTopic(message.getChatId()), message);
    }*/

}
