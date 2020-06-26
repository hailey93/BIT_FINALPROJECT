package com.bit.house.controller;

import com.bit.house.domain.CategoryVO;
import com.bit.house.domain.ColorVO;
import com.bit.house.service.CategoryService;
import com.bit.house.service.ColorService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/seller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @GetMapping("/category")
    public String category(Model model) {
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
//            log.info(jsonText);
//
//            log.info(jsonColor);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "th/seller/category";
    }

    @PostMapping(value = "/testCategory", produces = "application/json")
    public String testCategoryCode(String categoryCode, MultipartFile[] file, HttpServletRequest request) {

        String uploadFolder = request.getSession().getServletContext().getRealPath("image/test");

        for (MultipartFile multipartFile : file) {

            log.info(multipartFile.getOriginalFilename());

            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo((saveFile));

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return "th/seller/testCategory";
    }

    //
//    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
//    @ResponseBody
//    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
//
//        JsonObject jsonObject = new JsonObject();
//
//        String fileRoot = request.getSession().getServletContext().getRealPath("image/test");
//        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
//        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//
//        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
//
//        File targetFile = new File(fileRoot + savedFileName);
//
//        try {
//            InputStream fileStream = multipartFile.getInputStream();
//            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//            jsonObject.addProperty("url", savedFileName);
//            jsonObject.addProperty("responseCode", "success");
//
//        } catch (IOException e) {
//            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//            jsonObject.addProperty("responseCode", "error");
//            e.printStackTrace();
//        }
//
//        return jsonObject;
//    }


}
