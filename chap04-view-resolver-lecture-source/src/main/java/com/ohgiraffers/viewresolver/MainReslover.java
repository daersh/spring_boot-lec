package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")   //설명. 요청 경로상 첫번째 경로 스텝을 처리하는 컨트롤러다
public class MainReslover {
    /**
     * <h1>ViewResolver</h1>
     * 뷰 리졸버 인터페이스를 구현한 ThtneleafViewResolver가 현재 처리하게 된다.
     * <ul>
     * <li>접두사(prefix): resources/templates</li>
     * <li>접미사(suffix): .html</li>
     * </ul>
     * 핸들러 메소드가 반환하는 String 값 앞 뒤에 접두사와 접미사가 붙어 view를 찾게 된다.
     * */
    @RequestMapping({"/", "/main"})
    public String main(){
        return "main";
    }

}
