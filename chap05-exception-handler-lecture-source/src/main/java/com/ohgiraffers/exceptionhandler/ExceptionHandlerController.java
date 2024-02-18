package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")       //depth = 0
public class ExceptionHandlerController {
    @GetMapping("simple-null")
    public String simpleNullPointerExceptionTest(){

        if(true) {  //설명. 의도적으로 에러 내기 위함
            throw new NullPointerException();
        }
        return "/";
    }

    @GetMapping("simple-user")
    public String simpleUserExceptionTest() throws MemberRegistException {
        if (true){
            throw new MemberRegistException("당신같은 사람은 회원으로 안받을 겁니다.!");
        }
        return "/";
    }

    @GetMapping("annotation-null")
    public String annotationNullPointerExceptionTest(){
        String str = null;
        System.out.println("str.charAt(0) = " + str.charAt(0));
        return "/";
    }
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(){
        System.out.println("이 컨트롤러에서 널포인터 발생 시 확인 하기 위함");
        return "error/nullPointer";
    }

    @GetMapping("annotation-user")
    public String annotationUserExceptionTest() throws MemberRegistException {
        if(true){
            throw new MemberRegistException("당신은 자격 없어유~~~@_@");
        }
        return "/";
    }

    /*모델과 해당 예외를 활용할 수 있다.*/
    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception){
        model.addAttribute("exception",exception);
        return "error/memberRegist";
    }
}
