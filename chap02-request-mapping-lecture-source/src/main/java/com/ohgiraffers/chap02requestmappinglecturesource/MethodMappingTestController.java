package com.ohgiraffers.chap02requestmappinglecturesource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {
        /**<h1>Handler Method</h1>
     * 어노테이션을 활용하여 요청 방식 및 경로에 따라 각 메소드가 작성 된다.
    * */
    @RequestMapping(value = "/menu/regist",method = RequestMethod.GET)
    //@RequestMapping(value = "/menu/regist")//설명. Get 요청 뿐 아니라 다른 요청도 모두 처리가능해진다. (do,post,delte.. 다 받아들이게됨)
    public String registMenu(Model model){
        /*필기. model: 응답할 페이지의 재료를 담을 수 있는 객체이다.*/
        System.out.println("/menu/regist 경로의 GET 요청 받아보기");
        model.addAttribute("message","신규 메뉴 등록용 헨들러 메소드 호출함...");

        return "mappingResult";
    }

    @RequestMapping(value = "/menu/modify",method = RequestMethod.POST)
    public String modifyMenu(Model model){
        model.addAttribute("message","POST 방식의 메뉴 수정용 핸들러 메소드 호출함");
        return "mappingResult";
    }

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){
        model.addAttribute("message","GET 방식의 메뉴 삭제용 핸들러 메소드 호출함");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){
        model.addAttribute("message","POST 방식의 메뉴 삭제용 핸들러 메소드 호출함");
        return "mappingResult";
    }
}
