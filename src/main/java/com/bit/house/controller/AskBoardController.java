package com.bit.house.controller;

import com.bit.house.mapper.AskBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AskBoardController {

    @Autowired
    AskBoardMapper askBoardMapper;

    @RequestMapping("/list")//게시판 리스트 화면 호출
    private String askBoardList(Model model) throws Exception {

        model.addAttribute("list", askBoardMapper.boardList());

        return "th/askBoard/askBoardList";
    }

//    @RequestMapping("detail/{askBoardNo}") //글 상세페이지
//    private String askDetail(@PathVariable int askBoardNo, Model model) throws Exception{
//
//        model.addAttribute("detail", askBoardMapper.askDetail(askBoardNo));
//
//        return "th/askBoard/askBoardDetail";
//    }
//
//    @RequestMapping("/insert")//게시글 작성 폼 호출
//    private String insertAsk(){ return "th/askBoard/askBoardInsert"; }
//
//    @RequestMapping("/insertProc")//게시글 작성
//    private String insertAskProc(HttpServletRequest request, String editor) throws Exception{
//
//        AskBoardVO askBoardVO = new AskBoardVO();
//
//        askBoardVO.setAskTitle(request.getParameter("askTitle"));
//        askBoardVO.setMemberId(request.getParameter("memberId"));
//        //에디터 content 들어갈 자리
//
//        System.err.println("저장할 내용: " + editor);
//
//        askBoardMapper.insertAsk(askBoardVO, editor);
//
//        return "redirect:/list";
//    }
//
//    @GetMapping("/reply/{askBoardNo}")//답글 작성 폼
//    private String askReply(@PathVariable int askBoardNo, Model model) throws Exception{
//
//        model.addAttribute("detail", askBoardMapper.askDetail(askBoardNo));
//
//        return "th/askBoard/askBoardReplyInsert";
//    }
//
//    @RequestMapping("/replyProc")
//    private String askReplyProc(HttpServletRequest request, String editor) throws Exception{
//
//        AskBoardVO askBoardVO = new AskBoardVO();
//
//        askBoardVO.setAskTitle(request.getParameter("askTitle"));
//        askBoardVO.setMemberId(request.getParameter("memberId"));
//        //content들어갈 자리
//
//        askBoardMapper.askReply(askBoardVO, editor);
//
//        return "redirect:/list";
//    }
//
//    @RequestMapping("/update/{askBoardNo}")//게시글 수정 폼
//    private String askUpdate(@PathVariable int askBoardNo, Model model) throws Exception{
//
//        model.addAttribute("detail", askBoardMapper.askDetail(askBoardNo));
//
//        return "th/askBoard/askBoardUpdate";
//    }
//
//    @RequestMapping("/updateProc")
//    private String askUpdateProc(HttpServletRequest request, String editor) throws Exception{
//
//        AskBoardVO askBoardVO = new AskBoardVO();
//
//        askBoardVO.setAskTitle(request.getParameter("askTitle"));
//        askBoardVO.setMemberId(request.getParameter("memberId"));
//        //content들어갈 자리
//
//        askBoardMapper.askUpdate(askBoardVO, editor);
//
//        return "redirect:/detail/" + request.getParameter("askBoardNo");
//    }
//
//    @RequestMapping("/delete/{askBoardNo}")
//    private String askDelete(@PathVariable int askBoardNo) throws Exception{
//
//        askBoardMapper.askDelete(askBoardNo);
//
//        return "redirect:/list";
//    }

}