package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

    @GetMapping({"/","/main"})
    public String main(){
        return "main";
    }
//    @GetMapping("simple-null")
}
