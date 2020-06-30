package com.bit.house.controller;

import com.bit.house.domain.SellerVO;
import com.bit.house.mapper.SellerAdmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class SellerAdmController {

    @Autowired(required = false)
    SellerAdmMapper sellerAdmMapper;

    @RequestMapping("/sellerManagement")
    public String sellerManagement(Model model) throws Exception{

        model.addAttribute("sellerMng", sellerAdmMapper.sellerManagement());

        return "th/admin/statAdmin/sellerManagement";
    }

    @RequestMapping("/sellerStat/{sellerName}")
    public String sellerStat(@PathVariable String sellerName, Model model) throws Exception{

        model.addAttribute("sellerProduct", sellerAdmMapper.sellerProduct(sellerName));
        model.addAttribute("sellerStat", sellerAdmMapper.sellerStat(sellerName));
        model.addAttribute("productCount", sellerAdmMapper.productCount(sellerName));

        return "th/admin/statAdmin/sellerStat";
    }

    @RequestMapping("/sellerDetail")
    public String sellerDetail(@RequestParam(required = false) String sellerName, Model model) throws Exception{

        System.out.println("sellerName : "+sellerName);

        model.addAttribute("sellerDetail", sellerAdmMapper.sellerDetail(sellerName));

        return "th/admin/statAdmin/sellerDetail";
    }

    @RequestMapping("/allSellerProduct")
    public String sellerProduct(@RequestParam(required = false) String sellerProduct, Model model) throws Exception{

        model.addAttribute("product", sellerAdmMapper.allSellerProduct(sellerProduct));
        model.addAttribute("productCount", sellerAdmMapper.productCount(sellerProduct));

        return "th/admin/statAdmin/sellerProduct";
    }

    @RequestMapping("/applySeller")
    public String applySeller(Model model) throws Exception{

        model.addAttribute("applySeller", sellerAdmMapper.applySeller());

        return "th/admin/statAdmin/applySeller";
    }


    @RequestMapping("/applySellerDetail/{sellerName}")
    public String applySellerDetail(@PathVariable String sellerName, Model model) throws Exception{

        System.out.println("sellerName : "+sellerName);

        model.addAttribute("sellerDetail", sellerAdmMapper.applySellerDetail(sellerName));

        System.out.println("seller : "+sellerAdmMapper.applySellerDetail(sellerName));

        return "th/admin/statAdmin/applySellerDetail";
    }

    @RequestMapping("/applyProc")
    public String applyProc(@RequestParam(required = false) String sellerName, SellerVO sellerVO) throws Exception{

        sellerVO.setSellerName(sellerName);

        sellerAdmMapper.applyProc(sellerName);

        return "redirect:/admin/applySeller";
    }
}
