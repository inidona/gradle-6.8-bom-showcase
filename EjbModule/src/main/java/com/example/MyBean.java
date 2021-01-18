package com.example;

import org.apache.commons.lang3.StringUtils;

public class MyBean {

    public String sayHello(String to){
        return StringUtils.join("Hello", to, " ");
    }
}
