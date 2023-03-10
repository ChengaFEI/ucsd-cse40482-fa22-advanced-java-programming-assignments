javac com.chengfei.greet/module-info.java \
com.chengfei.greet/com/chengfei/greet/Greeter.java \
com.chengfei.greet/com/chengfei/greet/internal/GreeterImpl.java

javac --module-path com.chengfei.greet \
ch15.sec05/module-info.java \
ch15.sec05/com/chengfei/hello/Hello.java