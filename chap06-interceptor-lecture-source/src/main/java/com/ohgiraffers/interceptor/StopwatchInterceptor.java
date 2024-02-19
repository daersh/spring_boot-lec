package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
    * <h1>인터셉터 목적</h1>
 * 로그인 체크, 권한 체크, 프로그램 실행 시간 계산 작업 로그 처리, 업로드 파일 처리, 로케일(지역) 설정 등을 하기 위함
 * <ul>
 *     <li>1. preHandle: boolean 형에 따라 핸들러 메소드가 실행될 수 있고 안될 수도 있도록 할 수 있으며 핸들러 메소드 이전 전처리 목적이다.</li>
 *     <li>2. postHandle: </li>
 *     <li>3. afterCompletion: 핸들러 메소드 이후 뷰를 랜더링하여 뷰 처리가 끝난 후 동작한다.</li>
 * </ul>
 *
* */
@Configuration
public class StopwatchInterceptor implements HandlerInterceptor {
    /*중요. 필터와 달리 인터셉터는 빈을 활용할 수 있다. -> 서블릿의 필터와 스프링의 인터셉터와의 가장 큰 차이점*/
    private final MenuService MENUSERVICE;
    public StopwatchInterceptor(MenuService menuservice) {
        System.out.println("StopwatchInterceptor.StopwatchInterceptor");
        this.MENUSERVICE = menuservice;
    }

    /*목차 1.시작 전 발생. boolean 형에 따라 핸들러 메소드가 실행될 수 있고 안될 수도 있도록 할 수 있으며 핸들러 메소드 이전 전처리 목적이다.
        * 핸들러 인터셉터는 bean을 활용할 수 있다.(참고. @Service 계층 객체도 빈이다.)
            *  MENUSERVICE.method();
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MENUSERVICE.method();
        System.out.println("preHandle 호출함 (핸들러 메소드 이전)");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);

        /*리턴: 반환형을 false로 하면 특정 조건에 의해 이후 핸들러 메소드가 실행되지 않도록 할 수 있다. */
        return true;
    }


    /*목차 2.핸들러 메소드 이후*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 호출함(핸들러 메소드 이후)");
        long startTime = (long) request.getAttribute("startTime");  // 객체 반환하는 것이기 때문에 다운 캐스팅 필요
        long endTime = System.currentTimeMillis();
        request.removeAttribute("startTime");
        System.out.println("시작시간: "+startTime+"\n현재시간: "+endTime + "\n실행시간: " +(endTime-startTime));
        modelAndView.addObject("interval",endTime-startTime);
    }
    /*목차 3. 갔다 온 다음 뷰를 랜더링 다하고 뷰 처리가 끝난 후 - 리소스 반환할 때 사용 가능!*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
