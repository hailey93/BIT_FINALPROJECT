package com.bit.house.controller;

import com.bit.house.chattingProcess.ChatRepository;
import com.bit.house.chattingProcess.RedisPublisher;
import com.bit.house.domain.ChatRoomVO;
import com.bit.house.domain.ChatVO;
import com.bit.house.domain.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final RedisPublisher redisPublisher;
    private final ChatRepository chatRepository;

    @MessageMapping("/message")
    public void message(ChatVO message){
        if(ChatVO.MessageType.ENTER.equals(message.getType())){
            chatRepository.enterChatRoom(message.getChatId());
            message.setMsg(message.getSender()+"님이 입장하셨습니다.");

        }
        redisPublisher.publish(chatRepository.getTopic(message.getChatId()), message);
    }

    @GetMapping("/chatList")
    public String startChat(Model model, HttpSession session){
        //채팅리스트
        model.addAttribute("roomLists", chatRepository.findAllRoom());
        return "th/main/chatList";
    }
    /*@GetMapping("/chatroom")
    @ResponseBody
    public List<ChatRoomVO> getRoom(){
        //채팅방 목록
        //log.info("채팅방 리스트_controller"+chatRepository.findAllRoom());
        return chatRepository.findAllRoom();
    }*/

    @PostMapping("/chat")
    public String createChat(RedirectAttributes redirect, Model model, HttpSession session){
        //채팅방 생성
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        String memberId = memberVO.getMemberId();
        ChatRoomVO vo=chatRepository.createRoom(memberId);
        String chatId=vo.getChatId();

        return "redirect:/enter/"+chatId;
    }

    @GetMapping("/enter/{chatId}")
    public String enterChat(Model model, @PathVariable String chatId, HttpSession session){
        //채팅방 입장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ChatRoomVO vo=chatRepository.findRoombyId(chatId);

        if(String.valueOf(authentication.getAuthorities()).equals("[ROLE_ADMIN]")) {
            //관리자가 입장할때, 관리자 아이디 set
            vo.setAdminId(authentication.getName());
            log.info(vo.getAdminId());
        }
        model.addAttribute("chatInfo", vo);

        return "th/main/chatting";
    }

    /*@GetMapping("/chat/{chatId}")
    @ResponseBody
    public ChatRoomVO roomInfo(RedirectAttributes redirect, @PathVariable String chatId){
        //채팅방 조회
        redirect.addFlashAttribute("chatId", chatRepository.findRoombyId(chatId).getChatId());
        return chatRepository.findRoombyId(chatId);
    }*/


}
