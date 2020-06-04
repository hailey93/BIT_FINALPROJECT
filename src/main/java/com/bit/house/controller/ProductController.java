package com.bit.house.controller;

import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/productions/search")
    public String findProduct(@RequestParam(required = false, defaultValue = "") String index, String searchProducts, Model model){
        List<ProductVO> products = productMapper.selectAllProduct();

        if(index != null && !index.isEmpty()){
            products = productMapper.selectProduct(index);
        }else {
            products = productMapper.selectAllProduct();
        }

        model.addAttribute("index", index);
        model.addAttribute("mainList", products);

        return "th/main/storeMain";
    }

}
