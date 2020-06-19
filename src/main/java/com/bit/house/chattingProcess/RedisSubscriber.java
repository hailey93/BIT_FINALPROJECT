package com.bit.house.chattingProcess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Slf4j
@Service
public class RedisSubscriber implements MessageListener {

    /*private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;
*/
    //redis에서 메시지가 pub되면 대기하고 있던 onmessgae가 해당 메시지 처리
    @Override
    public void onMessage(Message message, byte[] pattern) {
        /*try{
            String publishMessage=(String)redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatVO roomMessage=objectMapper.readValue(publishMessage, ChatVO.class);
            messagingTemplate.convertAndSend("/sub/chat/room/"+roomMessage.getChatId(), roomMessage);

        } catch (Exception e) {
            log.error(e.getMessage());
        }*/
    }
}
