package com.bit.house.controller;

import com.bit.house.domain.ChatRoomVO;
import com.bit.house.chattingProcess.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRepository chatRepository;

    @GetMapping("/chat")
    public String startChat(Model model){
        //채팅리스트
        //model.addAttribute("roomLists", chatRepository.findAllRoom());
        return "th/main/chatting";
    }
    @GetMapping("/chatroom")
    @ResponseBody
    public List<ChatRoomVO> getRoom(){
        //채팅방 목록
        //log.info("채팅방 리스트_controller"+chatRepository.findAllRoom());
        return chatRepository.findAllRoom();
    }

    @PostMapping("/chat")
    @ResponseBody
    public ChatRoomVO createRoom(@RequestParam String roomName){
        //채팅방 생성
        log.info("채팅방 이름은??_controller"+roomName);
        return chatRepository.createRoom(roomName);
    }

    @GetMapping("/chat/enter/{chatId}")
    public String roomDetail(Model model, @PathVariable String chatId){
        //채팅방 입장화면
        model.addAttribute("chatId", chatId);
        return "th/main/chattingDetail";
    }

    @GetMapping("/chat/{chatId}")
    @ResponseBody
    public ChatRoomVO roomInfo(@PathVariable String chatId){
        //채팅방 조회
        return chatRepository.findRoombyId(chatId);
    }
}
