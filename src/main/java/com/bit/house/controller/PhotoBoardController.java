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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PhotoBoardController {

    @Autowired(required = false)
    PhotoBoardMapper photoBoardMapper;


    //사진 메인
    @RequestMapping("/comunityMain")
    private String communityMain(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        return "th/photoBoard/photoBoardMain";
    }
    //사진 목록
    @RequestMapping("/photoBoardList")
    private String photoBoardList(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        model.addAttribute("photoList", photoBoardMapper.photoBoardList());

        return "th/photoBoard/photoBoardList";
    }
    //사진 등록
    @RequestMapping("/photoinsert")//게시글 작성 폼 호출
    private String insertAsk() {
        return "th/photoBoard/photoBoardInsert";
    }

    @RequestMapping("/photoInsertProc")
    private String insertPhotoProc(PhotoBoardVO photoBoardVO,
                                   MultipartHttpServletRequest mtf, String memberId, HttpServletRequest request) throws Exception{

        List<String> photoImgArray = new ArrayList<String>();
        List<MultipartFile> fileList = mtf.getFiles("files");

        String filePath = request.getSession().getServletContext().getRealPath("image/board/photoboard/");

        for(MultipartFile mf : fileList){

            StringBuffer sb = new StringBuffer();
            //이렇게 안에다 쓸거면 stringBuffer를 굳이 쓸 필요없이 string을 쓰면 된다.

            System.out.println("파일리스트 : "+mf);

            String originFileName = mf.getOriginalFilename();

            String saveName =  sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
                                .append(UUID.randomUUID().toString())
                                .append(originFileName.substring(originFileName.lastIndexOf("."))).toString();

            System.out.println("경로 : "+filePath);

            System.out.println("저장명 : "+saveName);


            String saveFile = filePath + saveName;

            photoImgArray.add(saveName);

            mf.transferTo(new File(saveFile));

        }
        System.out.println("리스트 : "+photoImgArray);

        int filesSize=photoImgArray.size();
        if( filesSize !=0){
            int i=1;
            switch (filesSize ){
                case 5 :
                    photoBoardVO.setPhotoImg5( photoImgArray.get(filesSize-i)   );
                    i++;
                case 4 :
                    photoBoardVO.setPhotoImg4( photoImgArray.get(filesSize-i)   );
                    i++;
                case 3 :
                    photoBoardVO.setPhotoImg3( photoImgArray.get(filesSize-i)   );
                    i++;
                case 2 :
                    photoBoardVO.setPhotoImg2( photoImgArray.get(filesSize-i)   );
                    i++;
                case 1 :
                    photoBoardVO.setPhotoImg1( photoImgArray.get(filesSize-i)   );
            }
        }
        System.out.println("end");

        memberId = "youn123";

        photoBoardVO.setMemberId(memberId);
        photoBoardVO.setPhotoTitle(request.getParameter("photoTitle"));
        photoBoardVO.setPhotoContent(request.getParameter("photoContent"));

        photoBoardMapper.insertPhoto(photoBoardVO);

        System.out.println("OK");
        return "redirect:/photoBoardList";
    }
    //사진 상세
    @RequestMapping("/photodetail/{photoBoardNo}")
    private String photoDetail(@PathVariable int photoBoardNo, Model model, String memberId) throws Exception{

        model.addAttribute("photodetail", photoBoardMapper.photoDetail(photoBoardNo));
        model.addAttribute("userphoto", photoBoardMapper.userPhoto(memberId));
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
