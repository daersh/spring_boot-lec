package com.ohgiraffers.autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringConfiguration {
    @Value("${test.value}")
    public String value;

    @Value("${username}")
    public String name;
    @Value("${test.age}")
    public String age;
    @Value("${test.array}") // 설명. , 를 구분하여 리스트로 만들 수 있다.
    public List<String> list;
    @Value("${test.array}") // 설명. , 가 있어도 하나로 묶어서 볼 수 있음!
    public String listToSTring;
    @Value("${test.array}")
    public String[] strArr;

    @Bean
    public Object propertyReadTest(){
        System.out.println("value = " + value);
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        System.out.println("===== 설정에서 읽어들인 여러개의 값 처리");
        System.out.println(list.toString());
        System.out.println("listToSTring = " + listToSTring);
        System.out.print("strArr: ");
        Arrays.stream(strArr).forEach(System.out::println);

        return new Object();
    }

}
