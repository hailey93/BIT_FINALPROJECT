package com.bit.house.controller;

import com.bit.house.domain.CategoryVO;
import com.bit.house.domain.ColorVO;
import com.bit.house.domain.SellerVO;
import com.bit.house.service.CategoryService;
import com.bit.house.service.ColorService;
import com.bit.house.service.SellerService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/seller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SellerService sellerService;

    @Autowired
    ColorService colorService;

    @GetMapping("/category")
    public String category(Model model, @AuthenticationPrincipal Principal principal) {

        String id = principal.getName();
        SellerVO sellerInfo = sellerService.searchSellerInfo(id);
        model.addAttribute("sellerInfo", sellerInfo);

        ObjectMapper mapper = new ObjectMapper();
        String jsonText;
        String jsonColor;

        List<ColorVO> color = colorService.searchColor();
        List<CategoryVO> category = categoryService.searchCategory();

        try {
            jsonText = mapper.writeValueAsString(category);
            jsonColor = mapper.writeValueAsString(color);

            model.addAttribute("jsonText", jsonText);
            model.addAttribute("jsonColor", jsonColor);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "th/seller/category";
    }


    @PostMapping("/addProduct")
    @ResponseBody
    public void addProduct(MultipartFile[] productExpImgUrl, MultipartFile[] productMainImgUrl, HttpServletRequest request, String[] optionColor, String[] productQty){

        String uploadFolderSeller = request.getSession().getServletContext().getRealPath("image/product");
//
//        for(MultipartFile multipartFile : productExpImgUrl) {
//
//            File saveFile = new File(uploadFolderSeller, multipartFile.getOriginalFilename());
////            productExpImg = "/uploadImg/product/" + multipartFile.getOriginalFilename();
//
////            sellerService.updateSellerInfo(sellerName, sellerRes, sellerImg, sellerAddr, productExpImg, principal.getName());
//            try {
//                multipartFile.transferTo(saveFile);
//
//                log.info("success");
//
//            } catch (Exception e) {
//                log.error(e.getMessage());
//            }
//        }
       for(MultipartFile multipartFile : productExpImgUrl){
           log.info(multipartFile.getOriginalFilename());
       }

        for(MultipartFile multipartFile : productMainImgUrl){
            log.info(multipartFile.getOriginalFilename());
        }

       for(int i=0; i<optionColor.length; i++){
         log.info(optionColor[i]);
         log.info(productQty[i]);
       }

    }

}
