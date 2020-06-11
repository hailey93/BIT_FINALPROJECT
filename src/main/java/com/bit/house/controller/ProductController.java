package com.bit.house.controller;

import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/productions")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/searchlist")
    public String findProduct(@RequestParam(required = false, defaultValue = "") String index, Model model) {
        List<ProductVO> productList = productMapper.selectAllProduct();

        if (index != null && !index.isEmpty()) {
            productList = productMapper.selectProduct(index);
        } else {
            productList = productMapper.selectAllProduct();
        }

        model.addAttribute("productList", productList);
        model.addAttribute("index", index);
        return "th/main/searchList";
    }


    @GetMapping("/category")
    public String findByCategory(@RequestParam(value = "categoryCode", required = false) String category, Model model) {
        List<ProductVO> categoryList = productMapper.selectProductByCategory(category);
//8
        model.addAttribute("productList", categoryList);
        return "th/main/categoryList";
    }


}
