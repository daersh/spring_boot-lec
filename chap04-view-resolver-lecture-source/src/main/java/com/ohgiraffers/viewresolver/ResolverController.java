package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")   //depth1 인 애들 하겠다는뜻
public class ResolverController {
    /**<h1>1. 포워딩</h1>
     * 포워딩을 통해 서블릿과 마찬가지로 값을 전달 할 수 있다.
     * */
    @GetMapping("string")
    public String stringReturn(Model model){
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환");
        return "result";
    }
    /** <h1>2. 리다이렉트</h1>
     * 서블릿과 마찬가지로 파라미터를 활용하지 않고서는 리다이렉트를 통해 값을 전달할 수 있다.
     * <br> 하지만 스프링에 하는 방법이 있다.
     * */
    @GetMapping("string-redirect")
    public String stringRedirect(Model model){
        /*리다이렉트이기 때문에 이 속성(model)은 전달되지 못한다.*/
        model.addAttribute("message1", "문자열로 뷰 이름 반환하여 리다이렉트");
        return "redirect:/";
    }

    /**<h1>3. RedirectAttributes(리다이렉트속성전달)</h1>
     * 리다이렉트할 때 전달되지 않던 속성을 전달하고자 할 때 사용할 수 있는 클래스이다.
     * <br> 스프링 부트에서만 가능하고 기존 서블릿은 불가능하다.
     * */
    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr){
        rttr.addFlashAttribute("flashMessage1","리다이렉트 attr 사용하여 리다이렉트..");
        return "redirect:/";
    }
    /** <h1>4. ModelAndView(여러객체전달)</h1>
     * 기존에 핸들러 메소드가 void 또는 String으로만 반환했는데 ModelAndView를 반환할 수 있다.<br>
     * 객체들을 여러개 담을 때 여기에 담아서 전달하는 것이 편하다.<br>
     * 단순 포워딩 시에는 String반환과 Model을 활용하는 코드와는 별 차이 없다.
     * */
    @GetMapping("modelandview")
    public ModelAndView modelAndView(ModelAndView mv){
        mv.addObject("message2","modelandview를 이용한 포워딩");
        mv.setViewName("result");
        return mv;
    }

    /**
     * <h1>5. ModelAndView(리다이렉트)</h1>
     * modelandview를 통한 리다이렉트 시에는 addObject 한 것이 파라미터로 넘어간다.<br>
     * '?'가 있는 쿼리스트링 형태로 넘어간다. (?message2="modelandview를+이용한...");
     * */
    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){

        mv.addObject("message2", "modelandview를 이용한 리다이렉트");
        mv.setViewName("redirect:/");
        return mv;
    }


    /**
     * <h1>6. ModelAndView로 뷰 이름 봔한하며 리다이렉트, flash attr 사용하기</h1>
     * 5번 방식에서 새로고침을 하면 계속 alert창이 뜬다. 이를 방지하기 위해 사용한다.<br>
     *  한번 요청오고 새로고침해도 다시 뜨지 않는다!!!
     * */
    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttr(ModelAndView mv, RedirectAttributes rttr){
        rttr.addFlashAttribute("flashMessage2","ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;
    }


}
