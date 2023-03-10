package com.chengfei.hello;

import com.chengfei.greet.Greeter;

public class Hello {

    public static void main(String[] args) {
        Greeter greeter = Greeter.newInstance();
        System.out.println(greeter.greet("Modular World"));
    }
}