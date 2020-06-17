package com.bit.house.controller;

import com.bit.house.domain.CommentVO;
import com.bit.house.domain.LikeVO;
import com.bit.house.domain.PhotoBoardVO;
import com.bit.house.domain.ScrapVO;
import com.bit.house.mapper.PhotoBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class PhotoBoardController {

    @Autowired(required = false)
    PhotoBoardMapper photoBoardMapper;


    //사진 메인
    @RequestMapping("/comunitymain")
    private String communityMain(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        return "th/photoBoard/photoBoardMain";
    }
    //사진 목록
    @RequestMapping("/photoboardlist")
    private String photoBoardList(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        model.addAttribute("photolist", photoBoardMapper.photoBoardList());
        return "th/photoBoard/photoBoardList";
    }
    //사진 등록
    @RequestMapping("/photoinsert")//게시글 작성 폼 호출
    private String insertAsk() {
        return "th/photoBoard/photoBoardInsert";
    }

    @RequestMapping("/photoinsertProc")
    private String insertPhotoProc(PhotoBoardVO photoBoardVO, @RequestParam("photoTitle") String photoTitle,
                                   @RequestParam(required=false) List<MultipartFile> files) throws Exception{


        return "redirect:/photoBoardList";
    }
    //사진 상세
    @RequestMapping("/photodetail/{photoBoardNo}")
    private String photoDetail(PhotoBoardVO photoBoardVO, CommentVO commentVO) throws Exception{

        return "/th/photoBoard/photoBoardDetail";
    }
    //사진 수정
    @RequestMapping("/photoupdate")
    private String photoUpdate(PhotoBoardVO photoBoardVO) throws Exception{


        return "/th/photoBoard/photoBoardUpdate";
    }

    @RequestMapping("/photoupdateProc")
    private String photoUpdateProc(PhotoBoardVO photoBoardVO, HttpServletRequest request) throws Exception{

        return "redirect:/photoDetail/" + request.getParameter("photoBoardNo");
    }
    //사진 삭제
    @RequestMapping("/photodelete")
    private String photoDelete(@PathVariable int photoBoardNo) throws Exception{

        return "redirect:/photoBoardlist";
    }
    //좋아요
    @RequestMapping("/like")
    private String like(LikeVO likeVO) throws Exception{
        // ajax처리로 할건데 어떻게 할지 고민
        return "";
    }
    //좋아요 취소
    @RequestMapping("/nonlike")
    private String nonlike(LikeVO likeVO) throws Exception{

        return "";
    }
    //스크랩
    @RequestMapping("/scrap")
    private String scrap(ScrapVO scrapVO) throws Exception{
        //like와 마찬가지로 ajax
        return "";
    }
    //스크랩취소
    @RequestMapping("/nonscrap")
    private String nonscrap(ScrapVO scrapVO) throws Exception{

        return "";
    }
    //좋아요 누른사람 목록
    @RequestMapping("/likeview")
    private String likeView(Model model, LikeVO likeVO) throws Exception{

        return "/th/photoBoard/photoLike";
    }

    //다중파일업로드
    @RequestMapping(value = "/photouploader", method = RequestMethod.POST)
    @ResponseBody
    public String multiplePhotoUploader(HttpServletRequest request) {
        // 파일정보
        StringBuffer sb = new StringBuffer();
        try {
            // 파일명을 받는다 - 일반 원본파일명
            String oldName = request.getHeader("file-name");
            // 파일 기본경로 _ 상세경로
            String filePath = request.getSession().getServletContext().getRealPath("image/board/photoboard");   //  "D:/workspace/Spring/src/main/webapp/resources/photoUpload/";
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
                    .append("&sFileURL=").append("/uploadImg/board/photoboard").append(saveName);
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
