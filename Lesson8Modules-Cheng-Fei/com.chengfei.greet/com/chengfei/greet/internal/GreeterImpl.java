package com.chengfei.greet.internal;

import com.chengfei.greet.Greeter;

public class GreeterImpl implements Greeter {
    public String greet(String subject) {
        return "Hello " + subject + "!";
    }
}