package com.chengfei.greet;

public interface Greeter {
    static Greeter newInstance() {
        return new com.chengfei.greet.internal.GreeterImpl();
    }
    String greet(String subject);
}