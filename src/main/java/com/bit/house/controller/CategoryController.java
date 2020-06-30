package com.bit.house.controller;

import com.bit.house.domain.*;
import com.bit.house.service.CategoryService;
import com.bit.house.service.ColorService;
import com.bit.house.service.ProductService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @Autowired
    ProductService productService;

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
    public String addProduct(MultipartFile productMainImgUrl, MultipartFile[] productSubImgUrl, MultipartFile productExpImgUrl,
                           HttpServletRequest request, String productName, String modelName, String sellerName, String customerPrice,
                           String sellPrice, String purchasePrice, String categoryCode, String[] optionColor, String[] productQty,
                           ProductVO productVO, ProductOptionVO productOptionVO) throws IOException {

        String uploadFolderSeller = request.getSession().getServletContext().getRealPath("image/product");
        String productMainImg = productMainImgUrl.getOriginalFilename();
        String productExpImg = productExpImgUrl.getOriginalFilename();

        for(int i=0; i<productSubImgUrl.length; i++){
            String productSubImg1 = productSubImgUrl[0].getOriginalFilename();
            String productSubImg2 = productSubImgUrl[1].getOriginalFilename();
            String productSubImg3 = productSubImgUrl[2].getOriginalFilename();

            productVO.setProductSubImg1("product/" + productSubImg1);
            productVO.setProductSubImg2("product/" + productSubImg2);
            productVO.setProductSubImg3("product/" + productSubImg3);

            File saveFile = new File(uploadFolderSeller, productSubImgUrl[i].getOriginalFilename());
            try {
                productSubImgUrl[i].transferTo(saveFile);

                log.info("success");

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        productVO.setProductNo(modelName+"-"+categoryCode);
        productVO.setSellerName(sellerName);
        productVO.setProductName(productName);
        productVO.setModelName(modelName);
        productVO.setCustomerPrice(Integer.parseInt(customerPrice));
        productVO.setSellPrice(Integer.parseInt(sellPrice));
        productVO.setPurchasePrice(Integer.parseInt(purchasePrice));
        productVO.setCategoryCode(categoryCode);
        productVO.setProductMainImg("product/" + productMainImg);
        productVO.setProductExpImg("product/" + productExpImg);

        productService.insertProduct(productVO);

        for(int j=0; j<optionColor.length; j++){
            productOptionVO.setProductOptionNo(modelName+"-"+categoryCode+"-"+optionColor[j]);
            productOptionVO.setProductNo(modelName+"-"+categoryCode);
            productOptionVO.setColorCode(optionColor[j]);
            productOptionVO.setProductQty(Integer.parseInt(productQty[j]));
            productService.insertProductOption(productOptionVO);
        }

        File saveFileMain = new File(uploadFolderSeller, productMainImg);
        productMainImgUrl.transferTo(saveFileMain);
        File saveFileExp = new File(uploadFolderSeller, productExpImg);
        productExpImgUrl.transferTo(saveFileExp);

        return "redirect:/seller/category";
    }
}
