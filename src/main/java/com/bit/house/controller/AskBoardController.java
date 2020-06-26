package com.bit.house.controller;

import com.bit.house.domain.AskBoardVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.mapper.AskBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class AskBoardController {

    @Autowired(required = false)
    AskBoardMapper askBoardMapper;

    @RequestMapping("/askBoardList")//게시판 리스트 화면 호출
    private String askBoardList(Model model) throws Exception {

        model.addAttribute("list", askBoardMapper.askBoardList());

        return "th/askBoard/askBoardList";
    }

    @RequestMapping("askdetail/{askBoardno}") //글 상세페이지
    private String askDetail(@PathVariable int askBoardno, Model model) throws Exception {

        model.addAttribute("detail", askBoardMapper.askDetail(askBoardno));

        return "th/askBoard/askBoardDetail";
    }

    @RequestMapping("/askinsert")//게시글 작성 폼 호출
    private String insertAsk() {
        return "th/askBoard/askBoardInsert";
    }

    @RequestMapping("/askinsertProc")//게시글 작성
    private String insertAskProc(HttpServletRequest request, HttpSession session) throws Exception {

        AskBoardVO askBoardVO = new AskBoardVO();

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        askBoardVO.setAskTitle(request.getParameter("askTitle"));
        askBoardVO.setAskContent(request.getParameter("askContent"));
        askBoardVO.setMemberId(memberVO.getMemberId());

        askBoardMapper.insertAsk(askBoardVO);

        return "redirect:/askBoardList";
    }

    @GetMapping("/askreply/{askBoardno}")//답글 작성 폼
    private String askReply(@PathVariable int askBoardno, Model model) throws Exception {

        model.addAttribute("detail", askBoardMapper.askDetail(askBoardno));

        return "th/askBoard/askBoardReplyInsert";
    }

    @RequestMapping("/askreplyProc")
    private String askReplyProc(HttpServletRequest request, HttpSession session) throws Exception {

        AskBoardVO askBoardVO = new AskBoardVO();

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        askBoardVO.setAskTitle(request.getParameter("askTitle"));
        askBoardVO.setMemberId(memberVO.getMemberId());
        askBoardVO.setAskContent(request.getParameter("askContent"));
        askBoardVO.setAskGroupNo(Integer.parseInt(request.getParameter("askGroupNo")));
        askBoardVO.setAskIndent(Integer.parseInt(request.getParameter("askIndent")));

        askBoardMapper.askReply(askBoardVO);

        return "redirect:/askBoardList";
    }

    @RequestMapping("/askupdate/{askBoardno}")//게시글 수정 폼
    private String askUpdate(@PathVariable int askBoardno, Model model) throws Exception {

        model.addAttribute("detail", askBoardMapper.askDetail(askBoardno));

        return "th/askBoard/askBoardUpdate";
    }

    @RequestMapping("/askupdateProc")
    private String askUpdateProc(HttpServletRequest request) throws Exception {

        AskBoardVO askBoardVO = new AskBoardVO();

        askBoardVO.setAskTitle(request.getParameter("askTitle"));
        askBoardVO.setAskContent(request.getParameter("askContent"));
        askBoardVO.setAskBoardno(Integer.parseInt(request.getParameter("askBoardno")));

        askBoardMapper.askUpdate(askBoardVO);

        return "redirect:/askdetail/" + request.getParameter("askBoardno");
    }

    @RequestMapping("/askdelete/{askBoardno}")
    private String askDelete(@PathVariable int askBoardno) throws Exception {

        askBoardMapper.askDelete(askBoardno);

        return "redirect:/askBoardList";
    }


    // 다중파일업로드
    @RequestMapping(value = "/file_uploader_html5.do", method = RequestMethod.POST)
    @ResponseBody
    public String multiplePhotoUpload(HttpServletRequest request) {
        // 파일정보
        StringBuffer sb = new StringBuffer();
        try {
            // 파일명을 받는다 - 일반 원본파일명
            String oldName = request.getHeader("file-name");
            // 파일 기본경로 _ 상세경로
            String filePath = request.getSession().getServletContext().getRealPath("image/board/askboard/");   //  "D:/workspace/Spring/src/main/webapp/resources/photoUpload/";
            System.err.println(filePath);
            String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
                    .append(UUID.randomUUID().toString())
                    .append(oldName.substring(oldName.lastIndexOf("."))).toString();
            System.err.println(filePath + saveName);
            InputStream is = request.getInputStream();

            OutputStream os = new FileOutputStream(filePath + saveName);
            int numRead;
            byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
            while ((numRead = is.read(b, 0, b.length)) != -1) {
                os.write(b, 0, numRead);
            }
            os.flush();
            os.close();
            // 정보 출력
            sb = new StringBuffer();
            sb.append("&bNewLine=true")
                    .append("&sFileName=").append(oldName)
                    .append("&sFileURL=").append("/uploadImg/board/askboard/").append(saveName);
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
