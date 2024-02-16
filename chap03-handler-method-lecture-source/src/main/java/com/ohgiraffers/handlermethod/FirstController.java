package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Objects;

/*설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/xxx 의 요청을 받게 될 때 클래스에
 *  어노테이션을 추가할 수 있다.*/
@Controller()
@RequestMapping("/first")
public class FirstController {

    /*목차 1. 반환형이 void 인 핸들러 메소드는 요청 경로 자체가 view의 경로 및 이름을 반환한 것으로 바로 해석이 된다.*/
    @GetMapping("/regist") // 설명. '/' 넣어도 되고 안넣어도 ㄱㅊ
    //    public String regist(){return "regist" 아니면 "/first/regist"도 됨;}
    public void regist(){}

    /*설명. request를 쓰게 된다면 request 개념은 사용자의 입력값이 존재, 모델은 동적 페이지 재료 다믄ㄴ 재료로 쓰자*/
    @PostMapping("/regist")
    public String registMenu(WebRequest req , Model model){
        String name = req.getParameter("name");
        int price = Integer.parseInt(Objects.requireNonNull(req.getParameter("price"),"이렇게 하면 파라미터가 널이면 에러 조기 감지 가능, 메시지 남김"));
        int val = Integer.parseInt(Objects.requireNonNull(req.getParameter("categoryCode")));

        System.out.println("name = " + name);
        String message = name+" 메뉴 등록 성공 (가격: "+ price+ " 원, 카테고리 코드: "+ val+")" ;
        model.addAttribute("message",message);
        return "first/messagePrinter";
    }

    /** modify page로 이동하기 위한 메서드*/
    @GetMapping("/modify")
    public void modfiy(){}


    /** <h1>modify page에서 요청 처리하기 위한 메서드 (feat. @RequestParam)</h1>
     * 스프링 부트의 RequestParam 어노테이션을 이용하여 작성<br><br>
     *
     * <h2>중요! 생략해도 자동으로 적용된다.(default,name,defaultValue 사용 안하는 경우) </h2>
     * <br>
     * request의 파라미터로 넘어오는 값들의 키값과 일치하는 변수명을 작성하고, @RequestParam 어노테이션을 적용하면 알아서 값을 꺼내고,
     * 해당 매개변수의 자료형에 맞게 변환까지 해준다.
     * @RequestParm속성
     * @1.name: 만약 키값과 변수명을 다르게 할 경우 name="키값" 으로 하여 일치시킬 수 있다.
     * @2.defaultValue: 값이 입력값이 없거나("") 요청의 파라미터 키 값이 일치하지 않는 매개변수 명 사용 시 기본 default로 작성해준다
     * @3.required: false로 하면 만약 default도 없고, name 인자가 온 것이 아예 없다면 에러를 띄울 수 있다.
     * */
    @PostMapping("/modify")
    public String modifyMenu(Model model, @RequestParam(name = "name",defaultValue = "default",required = false) String modifyName, @RequestParam(defaultValue = "0",required = false) int modifyPrice){
        String message = modifyName+" 메뉴 등록 성공 (가격: "+ modifyPrice+ " 원)";
        model.addAttribute("message",message);
        return "first/messagePrinter";
    }

    @PostMapping("/modify2")
    public String modifyMenu2(Model model, @RequestParam Map<String, String> parameter){
        String modifyName = parameter.get("name2");
        int modifyPrice = Integer.parseInt(parameter.get("modifyPrice2"));
        String message = modifyName+" 메뉴 등록 성공 (가격: "+ modifyPrice+ " 원)";
        model.addAttribute("message",message);
        return "first/messagePrinter";
    }

}
