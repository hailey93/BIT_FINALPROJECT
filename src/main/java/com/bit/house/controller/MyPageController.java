package com.bit.house.controller;

import com.bit.house.domain.*;
import com.bit.house.mapper.MemberInfoMapper;
import com.bit.house.mapper.MyPageMapper;
import com.bit.house.service.MemberService;
import com.bit.house.service.MyPageService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
//@RequestMapping("member")
public class MyPageController {

    @Autowired(required = false)
    MemberInfoMapper memberInfoMapper;

    @Autowired(required = false)
    MyPageMapper myPageMapper;

    @Autowired(required = false)
    MyPageService myPageService;

    @Autowired
    private MemberService memberService;


    //프로필설정
    @RequestMapping("/myProfile")
    private String viewProfile(Model model, String memberId) throws Exception{

        memberId = "youn123";

        model.addAttribute("myprofile", myPageMapper.selectProfile(memberId));

        System.out.println(myPageMapper.selectProfile(memberId));

        return "th/member/mypage/profile/profileInfo";
    }

    //프로필수정
    @RequestMapping("/modifyProfile")
    private String modifyProfile(MemberVO memberVO, HttpServletRequest request, MultipartHttpServletRequest mreq) throws Exception{

        memberVO.setMemberId("youn123");
        memberVO.setNickName(request.getParameter("nickName"));
        memberVO.setMemberIntro(request.getParameter("memberIntro"));

        StringBuffer sb = new StringBuffer();

        String src = mreq.getParameter("src");
        MultipartFile mf = mreq.getFile("uploadFile");

        String oldName = mf.getOriginalFilename();
        String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
                            .append(UUID.randomUUID().toString())
                            .append(oldName.substring(oldName.lastIndexOf("."))).toString();

        String filePath = request.getSession().getServletContext().getRealPath("image/profileImg/");
        File dest = new File(filePath+saveName);

        int Img_WIDTH = 300;
        int Img_HEIGHT = 300;

        //이미지 리사이즈

        String img = dest.toString();

        memberVO.setMemberImg("/profileImg/"+saveName);
        System.out.println(img);

        myPageMapper.modifyProfile(memberVO);

        return "redirect:/myProfile";
    }
    //팔로워메뉴
    @RequestMapping("/viewFollow")
    private String viewFollow(Model model) throws Exception{

        model.addAttribute("follower", myPageMapper.follower());
        model.addAttribute("following", myPageMapper.following());

        return "th/member/mypage/profile/viewFollow";
    }
    //팔로우 팔로잉 전체보기 html 하나로 합쳐볼것.
    @RequestMapping("/allFollow")
    private String allFollow(Model model) throws Exception{

        model.addAttribute("follow", myPageMapper.follower());

        return "th/member/mypage/profile/allFollow";
    }
    //팔로잉 전체보기
    @RequestMapping("allFollowing")
    private String allFollowing(Model model) throws Exception{

        model.addAttribute("following", myPageMapper.following());

        return "th/member/mypage/profile/allFollowing";
    }
    //팔로우
    @RequestMapping("/follow")
    @ResponseBody
    private String follow(HttpServletRequest request, FollowVO followVO, String memberId, @RequestParam("followId") String followId, HttpSession session, String followNo) throws Exception{

        memberId = "jung123";
        followNo = memberId+followId;


        followVO.setMemberId(memberId);
        followVO.setFollowId(followId);
        followVO.setFollowNo(followNo);


        System.out.println("followNo : "+followNo);
        System.out.println("followId : "+followId);

        myPageMapper.follow(followVO);

        return "redirect:/memberProfile";
    }

    //팔로우취소
    @RequestMapping("/cancelFollow")
    @ResponseBody
    private void cancelFollow(@RequestParam("followId") String followId, String memberId, FollowVO followVO) throws Exception{

        memberId="jung123";
        followVO.setMemberId(memberId);
        followVO.setFollowId(followId);

        System.out.println("followId : "+followId);

        myPageMapper.cancelFollow(followVO);


    }
    //내 프로필
    @RequestMapping("/myBoard")
    private String myProfile(Model model, HttpSession session, String memberId) throws Exception{

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        memberId = "youn123";

        model.addAttribute("myphoto", myPageMapper.profilePhoto(memberId));
        model.addAttribute("mprofile", myPageMapper.myProfile(memberId));
        model.addAttribute("myscrap", myPageMapper.profileScrap(memberId));
        model.addAttribute("followCount", myPageMapper.followCount(memberId));
        model.addAttribute("followingCount", myPageMapper.followingCount(memberId));
        model.addAttribute("photoCount", myPageMapper.photoCount(memberId));
        model.addAttribute("scrapCount", myPageMapper.scrapCount(memberId));


        return "th/member/mypage/profile/myBoard";
    }

    //사용자 프로필
    @RequestMapping("/memberProfile")
    private String memberProfile(Model model, /*@PathVariable*/ String userId, String memberId) throws Exception{

        userId = "youn123";
        memberId = "jung123";

        model.addAttribute("memprofile", myPageMapper.myProfile(userId));
        model.addAttribute("memphoto", myPageMapper.profilePhoto(userId));
        model.addAttribute("memberscrap", myPageMapper.profileScrap(userId));
        model.addAttribute("followCount", myPageMapper.followCount(userId));
        model.addAttribute("followingCount", myPageMapper.followingCount(userId));
        model.addAttribute("photoCount", myPageMapper.photoCount(userId));
        model.addAttribute("scrapCount", myPageMapper.scrapCount(userId));
        model.addAttribute("fcount", myPageMapper.followerCount(memberId, userId));

        System.out.println(myPageMapper.profileScrap(userId).get(0).getScrapNo());
        return "th/member/mypage/profile/memberProfile";
    }
    //사진 게시글 전체보기
    @RequestMapping("/allPhoto")
    private String allPhoto(Model model, @RequestParam(required = false) String memberId) throws Exception{
        //이거는 세션 받지 말고 프로필창에서 아이디 넘겨받아서 처리하는쪽으로

        System.out.println(memberId);
        model.addAttribute("photo", myPageMapper.allPhoto(memberId));

        return "th/member/mypage/profile/allPhoto";
    }

    //스크랩 전체보기
    @RequestMapping("/allScrap")
    private String allScrap(Model model, @RequestParam(required = false) String memberId) throws Exception{
        //이것도 세션안받음
        System.out.println(memberId);
        model.addAttribute("scrap", myPageMapper.allScrap(memberId));

        return "th/member/mypage/profile/allScrap";
    }

    //보낸쪽지함
    @RequestMapping("/sendNote")
    private String sendNote(Model model) throws Exception{

        model.addAttribute("sendnote", myPageMapper.sendNote("youn123"));

        return "th/member/mypage/profile/sendNote";
    }

    //받은쪽지함
    @RequestMapping("/receiveNote")
    private String receiveNote(Model model) throws Exception{

        model.addAttribute("receiveNote", myPageMapper.receiveNote("youn123"));

        return "th/member/mypage/profile/receiveNote";
    }

    //쪽지보내기 폼
    @RequestMapping("/noteSending")
    private String noteSending(Model model, @RequestParam(required = false) String receiveId){

        System.out.println("id : "+receiveId);
        model.addAttribute("sending", receiveId);
        System.out.println("model end");
        System.out.println(model);

        return "th/member/mypage/profile/noteSending";
    }

    //쪽지보내기
    @RequestMapping("/noteSendingProc")
    private String noteSendingProc(MsgVO msgVO, HttpServletRequest request) throws Exception{

        msgVO.setMemberId(request.getParameter("memberId")); //내 아이디 = 세션처리
        msgVO.setMsgContent(request.getParameter("msgContent"));
        msgVO.setReceiveId(request.getParameter("memberId"));

        myPageMapper.noteSending(msgVO);

        return "redirect:/sendNote";
    }

    //쪽지 삭제

    @RequestMapping("/deleteNote")
    @ResponseBody
    private String deleteNote(@RequestParam(required = false) List<String> msgNum) throws Exception{

        System.out.println(msgNum);

        myPageService.deleteNote(msgNum);

        return "redirect:/sendNote";
    }


    @GetMapping("/profile")
    public String getProfileInfo(HttpSession session, Model model) {
        MemberVO member = (MemberVO) session.getAttribute("memberVO");
        System.out.println(member);
        model.addAttribute("member", member);
        return "th/member/mypage/info/myInfoSetup";
    }

    @PostMapping("update")
    public String updateProfileInfo(HttpSession session,MemberVO memberVO,Model model) {
        memberInfoMapper.updateInfoMemberById(memberVO);
       session.setAttribute("memberVO", memberVO);
        return "redirect:/user/profile";
    }

//    @PostMapping("updatePassword")
//    public String updateProfilePassword(MemberVO memberVO){
//        memberService.insertMemberToUser(memberVO);
//        memberInfoMapper.updateInfoMemberById(memberVO);
//        return "redirect:/user/profile";
//    }


}