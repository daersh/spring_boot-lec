package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Objects;

/*설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/xxx 의 요청을 받게 될 때 클래스에
 *  어노테이션을 추가할 수 있다.*/
@Controller()
@RequestMapping("/first")
/*이 Controller 클래스의 핸들러 메소드에서 모델에 id라는 키값으로 담는 값들을 Session에 담으라는 어노테이션이다.*/
@SessionAttributes("id")
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

    @GetMapping("search")
    public void search(){
    }
    /** <h1>@ModelAttribute를 이용한 객체 전달</h1>
     * 핸들러 메소드에 우리가 작성한 클래스를 매개변수로 작성하면 스프링이 객체를 만들어 주고 setter로 값도 주입해준다.(커맨드 객체)<br>
     * ModelAttribute 어노테이션을 활용하면 커맨드 객체를 모델에도 담아주며 어트리뷰트의 키값을 저장할 수 있다.(키 값이 없으면 타입의 낙타봉 표기법이 키값이다.)
     * */
    @PostMapping("search")
    //    public String searchMenu(@ModelAttribute("menu") MenuDTO menuDTO,Model model){    // 이 방식에서 아래로 개선
    public String searchMenu(@ModelAttribute("menu") MenuDTO menuDTO){
        System.out.println("menuDTO = " + menuDTO);

        return "first/searchResult";
    }

    @GetMapping("login")
    public void login(){};

    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam String id){
        //설명. 세선에 아이디 올려두기 위함
        session.setAttribute("id",id);
        return"first/loginResult";
    }
    @GetMapping("logout1")
    public String logoutTest1(HttpSession session){
        session.invalidate();
        return"first/loginResult";
    }


    /**<h1>3.Model을 통한 세션 저장</h1>
     * '@SessionAttributes' 어노테이션을 통해 model에 담겨 있는 "id" 키를 꺼내서 세션에 저장할 수 있다.
     * */

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){
        model.addAttribute("id",id);
        return "first/loginResult";
    }
    /**<h2>3-1SessionStatus</h2>
     * 이 어노테이션을 통해 세션에 담긴 값은 SessionStatus에서 제공하는 setComplete()로 만료시켜야한다.
     * */
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){
        /* setCoplete(): 세션 종료 시키기 위함*/
        sessionStatus.setComplete();
        return "first/loginResult";
    }

    /**
     * <h1>4.@RequestBody 등의 핸들러 메소등의 어노테이션들을 활용한 전달 받기 </h1>
     * */
    @GetMapping("body")
    public void getBody(){};

    @PostMapping("body")
    public void body(@RequestBody String body, @RequestHeader("content-type") String contentType,
                     @CookieValue(value = "JSESSIONID") String sessionID){
        System.out.println("body = " + body);
        System.out.println("contentType = " + contentType);
        System.out.println("sessionID = " + sessionID);
    }


}
