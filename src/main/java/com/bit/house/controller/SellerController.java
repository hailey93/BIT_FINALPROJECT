package com.bit.house.controller;

import com.bit.house.domain.SellerVO;
import com.bit.house.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;

@Slf4j
@Controller
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping("/signupSeller")
    public String signupSeller(){


        return "th/login/signupSeller";

    }

    @PostMapping("/signupSellerToSU")
    public String signupSellerToSU(SellerVO sellerVO){

        sellerService.insertSellerToUser(sellerVO);
        sellerService.insertSeller(sellerVO);


        return "th/login/signupSeller";
    }

    @GetMapping("/testSeller")
    @ResponseBody
    public SellerVO testSeller(@AuthenticationPrincipal Principal principal){

        String id = principal.getName();
        sellerService.searchSeller(id);

        return sellerService.searchSeller(id);
    }

    @GetMapping("/seller/sellerInfo")
    public String sellerInfo(@AuthenticationPrincipal Principal principal, Model model){

        String id = principal.getName();
        SellerVO sellerInfo = sellerService.searchSellerInfo(id);

        model.addAttribute("sellerInfo", sellerInfo);

        return "th/seller/sellerInfo";
    }

    @PostMapping("/seller/uploadImg")
    public String testCategoryCode(MultipartFile[] file, HttpServletRequest request) {

        String uploadFolder = request.getSession().getServletContext().getRealPath("/image/seller");

        for(MultipartFile multipartFile : file) {

            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

            log.info(multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo((saveFile));

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return "redirect:/seller/sellerInfo";
    }


}
