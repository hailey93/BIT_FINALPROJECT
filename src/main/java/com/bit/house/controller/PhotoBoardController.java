package com.bit.house.controller;

import com.bit.house.domain.*;
import com.bit.house.mapper.PhotoBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping("/communityMain")
    private String communityMain(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        model.addAttribute("likerank", photoBoardMapper.communityMain());

        return "th/photoBoard/photoBoardMain";
    }
    //사진 목록
    @RequestMapping("/photoBoardList")
    private String photoBoardList(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        model.addAttribute("photoList", photoBoardMapper.photoBoardList());

        return "th/photoBoard/photoBoardList";
    }
    //사진 등록
    @RequestMapping("/photoInsert")//게시글 작성 폼 호출
    private String insertAsk(Model model, PhotoBoardVO photoBoardVO) throws Exception{

        model.addAttribute("areaCode", photoBoardMapper.area());
        model.addAttribute("houseCode", photoBoardMapper.house());
        model.addAttribute("styleCode", photoBoardMapper.style());
        model.addAttribute("placeCode", photoBoardMapper.place());

        return "th/photoBoard/photoBoardInsert";
    }

    @RequestMapping("/photoInsertProc")
    private String insertPhotoProc(PhotoBoardVO photoBoardVO,
                                   MultipartHttpServletRequest mtf, HttpSession session, HttpServletRequest request) throws Exception{

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
                    photoBoardVO.setPhotoImg5( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 4 :
                    photoBoardVO.setPhotoImg4( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 3 :
                    photoBoardVO.setPhotoImg3( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 2 :
                    photoBoardVO.setPhotoImg2( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 1 :
                    photoBoardVO.setPhotoImg1( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
            }
        }
        System.out.println("end");

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        photoBoardVO.setMemberId(memberVO.getMemberId());
        photoBoardVO.setPhotoTitle(request.getParameter("photoTitle"));
        photoBoardVO.setPhotoContent(request.getParameter("photoContent"));
        photoBoardVO.setAreaCode(request.getParameter("areaCode"));
        photoBoardVO.setHouseCode(request.getParameter("houseCode"));
        photoBoardVO.setStyleCode(request.getParameter("styleCode"));
        photoBoardVO.setPlaceCode(request.getParameter("placeCode"));


        System.out.println("areaCode : " + photoBoardVO.getAreaCode());
        System.out.println("houseCode : " + photoBoardVO.getHouseCode());
        System.out.println("styleCode : " + photoBoardVO.getStyleCode());
        System.out.println("placeCode : " + photoBoardVO.getPlaceCode());

        photoBoardMapper.insertPhoto(photoBoardVO);

        System.out.println("OK");
        return "redirect:/photoBoardList";
    }
    //사진 상세
    @RequestMapping("/photodetail/{photoBoardNo}")
    private String photoDetail(@PathVariable int photoBoardNo, Model model, String userId, PhotoBoardVO photoBoardVO, HttpServletRequest request, HttpSession session) throws Exception{
        //아이디값은 멤버아이디가 아닌 userId로 받아오고 세션에 내 아이디넣어서 넘겨주고 버튼 생성.


        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        PhotoBoardVO detail=photoBoardMapper.photoDetail(photoBoardNo);

        model.addAttribute("member", photoBoardMapper.myProfileImg(memberVO.getMemberId()));
        model.addAttribute("photodetail", detail);
        model.addAttribute("userphoto", photoBoardMapper.userPhoto(detail.getMemberId()));
        model.addAttribute("likestat", photoBoardMapper.likeStat(memberVO.getMemberId(), photoBoardNo));
        model.addAttribute("scrapstat", photoBoardMapper.scrapStat(memberVO.getMemberId(), photoBoardNo));
        model.addAttribute("photoComment", photoBoardMapper.photoComment(photoBoardNo));
        model.addAttribute("commentCount", photoBoardMapper.commentCount(photoBoardNo));

        System.out.println("아이디 : "+memberVO.getMemberId()+"글번호 : "+photoBoardNo+"likestat : "+photoBoardMapper.likeStat(memberVO.getMemberId(), photoBoardNo));

        return "th/photoBoard/photoBoardDetail";
    }
    //사진 수정
    @RequestMapping("/photoupdate/{photoBoardNo}")
    private String photoUpdate(@PathVariable int photoBoardNo, Model model) throws Exception{

        model.addAttribute("photodetail", photoBoardMapper.photoDetail(photoBoardNo));
        model.addAttribute("areaCode", photoBoardMapper.area());
        model.addAttribute("houseCode", photoBoardMapper.house());
        model.addAttribute("styleCode", photoBoardMapper.style());
        model.addAttribute("placeCode", photoBoardMapper.place());

        return "th/photoBoard/photoBoardUpdate";
    }

    @RequestMapping("/photoupdateProc")
    private String photoUpdateProc(PhotoBoardVO photoBoardVO,
                                   MultipartHttpServletRequest mtf, HttpSession session, HttpServletRequest request) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        List<String> photoImgArray = new ArrayList<String>();
        List<MultipartFile> fileList = mtf.getFiles("files");

        String filePath = request.getSession().getServletContext().getRealPath("image/board/photoboard/");

        for(MultipartFile mf : fileList){

            StringBuffer sb = new StringBuffer();

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
                    photoBoardVO.setPhotoImg5( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 4 :
                    photoBoardVO.setPhotoImg4( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 3 :
                    photoBoardVO.setPhotoImg3( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 2 :
                    photoBoardVO.setPhotoImg2( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
                    i++;
                case 1 :
                    photoBoardVO.setPhotoImg1( "/board/photoboard/"+photoImgArray.get(filesSize-i)   );
            }
        }
        System.out.println("end");



        photoBoardVO.setMemberId(memberVO.getMemberId());
        photoBoardVO.setPhotoTitle(request.getParameter("photoTitle"));
        photoBoardVO.setPhotoContent(request.getParameter("photoContent"));
        photoBoardVO.setAreaCode(request.getParameter("areaCode"));
        photoBoardVO.setHouseCode(request.getParameter("houseCode"));
        photoBoardVO.setStyleCode(request.getParameter("styleCode"));
        photoBoardVO.setPlaceCode(request.getParameter("placeCode"));


        System.out.println("areaCode : " + photoBoardVO.getAreaCode());
        System.out.println("houseCode : " + photoBoardVO.getHouseCode());
        System.out.println("styleCode : " + photoBoardVO.getStyleCode());
        System.out.println("placeCode : " + photoBoardVO.getPlaceCode());

        photoBoardMapper.updatePhoto(photoBoardVO);

        System.out.println("OK");



        return "redirect:/photoBoardList";
    }
    //사진 삭제
    @RequestMapping("/photodelete/{photoBoardNo}")
    private String photoDelete(@PathVariable int photoBoardNo) throws Exception{

        photoBoardMapper.deletePhoto(photoBoardNo);

        return "redirect:/photoBoardList";
    }
    //좋아요
    @RequestMapping("/like")
    @ResponseBody
    private void like(LikeVO likeVO, HttpSession session, @RequestParam("photoBoardNo") int photoBoardNo, String likeNo) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        likeNo = memberVO.getMemberId() + photoBoardNo;

        likeVO.setMemberId(memberVO.getMemberId());
        likeVO.setLikeNo(likeNo);
        likeVO.setPhotoBoardNo(photoBoardNo);

        photoBoardMapper.like(likeVO);

    }
    //좋아요 취소
    @RequestMapping("/nonlike")
    @ResponseBody
    private void cancellike(LikeVO likeVO, HttpSession session, @RequestParam("photoBoardNo") int photoBoardNo) throws Exception{

        MemberVO memberVo = (MemberVO) session.getAttribute("memberVO");

        likeVO.setMemberId(memberVo.getMemberId());
        likeVO.setPhotoBoardNo(photoBoardNo);

        photoBoardMapper.cancelLike(likeVO);
    }
    //스크랩
    @RequestMapping("/scrap")
    @ResponseBody
    private void scrap(ScrapVO scrapVO, HttpSession session, @RequestParam("photoBoardNo") int photoBoardNo) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        scrapVO.setMemberId(memberVO.getMemberId());
        scrapVO.setPhotoBoardNo(photoBoardNo);

        photoBoardMapper.scrap(scrapVO);

    }
    //스크랩취소
    @RequestMapping("/nonscrap")
    @ResponseBody
    private void cancelscrap(ScrapVO scrapVO, HttpSession session, @RequestParam("photoBoardNo") int photoBoardNo) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        scrapVO.setMemberId(memberVO.getMemberId());
        scrapVO.setPhotoBoardNo(photoBoardNo);

        photoBoardMapper.cancelScrap(scrapVO);

    }
    //좋아요 누른사람 목록
    @RequestMapping("/likeview")
    private String likeView(Model model, LikeVO likeVO) throws Exception{

        return "th/photoBoard/photoLike";
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

    @RequestMapping("/insertPhotoComment")
    @ResponseBody
    private void insertComment(@RequestParam("commentContent") String commentContent, CommentVO commentVO, HttpSession session, @RequestParam("photoBoardNo") int photoBoardNo) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        System.out.println("내용 : "+commentContent);
        System.out.println("글번호 : "+photoBoardNo);

        commentVO.setMemberId(memberVO.getMemberId());
        commentVO.setCommentContent(commentContent);
        commentVO.setPhotoBoardNo(photoBoardNo);

        photoBoardMapper.insertPhotoComment(commentVO);

    }

}
