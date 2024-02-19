package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** <h1> WebConfiguration</h1>
 * Interceptor 추가 및 static(정적) 리소스 호출 경로 등록 설정하기 위한 클래스이다.
 * <ul>
 *     StopwatchInterceptor 인터셉터 추가하기 위한 것이다
 * </ul>
 * */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private StopwatchInterceptor stopwatchInterceptor;
    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }
    /*인터셉터를 등록해야 실제로 동작할 수 있다.*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //참고. excludePathPatterns: 이를 등록하면 등록해준 경로의 요청은 인터셉터가 가로채지 않는다.
        registry.addInterceptor(stopwatchInterceptor).excludePathPatterns("/css/**");
    }

}
