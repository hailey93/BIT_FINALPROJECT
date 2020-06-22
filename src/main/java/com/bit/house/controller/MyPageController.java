package com.bit.house.controller;

import com.bit.house.domain.*;
import com.bit.house.mapper.MemberInfoMapper;
import com.bit.house.mapper.MyPageMapper;
import com.bit.house.service.MyPageService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class MyPageController {

    @Autowired(required = false)
    MemberInfoMapper memberInfoMapper;

    @Autowired(required = false)
    MyPageMapper myPageMapper;

    @Autowired(required = false)
    MyPageService myPageService;


    //프로필설정
    @RequestMapping("/myProfile")
    private String viewProfile(Model model) throws Exception{

        //MemberVO memberVO = myPageMapper.selectProfile("youn123");
        model.addAttribute("profile", myPageMapper.selectProfile("youn123"));

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
        mf.transferTo(dest);

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
    private String follow(HttpServletRequest request, FollowVO followVO, @PathVariable String memberId) throws Exception{

        followVO.setFollowId(request.getParameter(memberId));

        myPageMapper.follow(memberId);
        return "";  //어느페이지에서 하던 페이지 변경없이 되도록 설정해야함.
    }

    //팔로우취소
    @RequestMapping("/cancelFollow")
    private String cancelFollow(@PathVariable String memberId) throws Exception{

        myPageMapper.cancelFollow(memberId);

        return "";
    }
    //내 프로필
    @RequestMapping("/myBoard")
    private String myProfile(Model model) throws Exception{

        model.addAttribute("profile", myPageMapper.myProfile());
        model.addAttribute("mphoto", myPageMapper.profilePhoto());
        model.addAttribute("mscrap", myPageMapper.profileScrap());
        model.addAttribute("followCount", myPageMapper.followCount());
        model.addAttribute("followingCount", myPageMapper.followingCount());
        model.addAttribute("photoCount", myPageMapper.photoCount());
        model.addAttribute("scrapCount", myPageMapper.scrapCount());

        return "th/member/mypage/profile/myBoard";
    }
    //이것도 내 프로필이랑 같은 페이지에서 처리 가능할지 확인
    @RequestMapping("/memberProfile")
    private String memberProfile(Model model, @PathVariable String memberId) throws Exception{


        model.addAttribute("memprofile", myPageMapper.myProfile());
        model.addAttribute("mphoto", myPageMapper.profilePhoto());
        model.addAttribute("mscrap", myPageMapper.profileScrap());
        model.addAttribute("followCount", myPageMapper.followCount());
        model.addAttribute("followingCount", myPageMapper.followingCount());
        model.addAttribute("photoCount", myPageMapper.photoCount());
        model.addAttribute("scrapCount", myPageMapper.scrapCount());

        return "th/member/mypage/profile/memberProfile";
    }
    //사진 게시글 전체보기
    @RequestMapping("/allPhoto")
    private String allPhoto(Model model, @PathVariable String memberId) throws Exception{
        //이거는 세션 받지 말고 프로필창에서 아이디 넘겨받아서 처리하는쪽으로

        model.addAttribute("photo", myPageMapper.allPhoto());

        return "th/member/mypage/profile/allPhoto";
    }

    //스크랩 전체보기
    @RequestMapping("/allScrap")
    private String allScrap(Model model, @PathVariable String memberId) throws Exception{
        //이것도 세션안받음

        model.addAttribute("scrap", myPageMapper.allScrap());

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
        msgVO.setMsgContents(request.getParameter("msgContent"));
        msgVO.setReceiveId(request.getParameter("memberId"));

        myPageMapper.noteSending(msgVO);

        return "redirect:/sendNote";
    }

    //쪽지 삭제

    @RequestMapping("/deleteNote")
    private String deleteNote(@RequestParam(required = false) List<String> msgNum) throws Exception{

        System.out.println(msgNum);

        myPageService.deleteNote(msgNum);

        return "redirect:/sendNote";
    }

    @GetMapping("/user/{memberId}")
    public String getProfileInfo(@PathVariable String memberId, Model model){

        model.addAttribute("member", memberInfoMapper.getInfoMemberById(memberId));
        return "th/member/mypage/info/myInfoSetup";
    }

    @PostMapping("/user")
    public String updateMemberInfo(@AuthenticationPrincipal MemberVO memberVO, Model model){



        return "redirect:/user";
    }



}
