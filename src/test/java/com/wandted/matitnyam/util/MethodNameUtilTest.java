package com.wandted.matitnyam.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MethodNameUtilTest {

    @DisplayName("쓰레드로 현재 메소드 이름 조회하기 성공")
    @Test
    public void getMethodNameWithThread(){
        String methodName = "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace){
            if(stackTraceElement.getClassName().equals(this.getClass().getName())){
                methodName = stackTraceElement.getMethodName();
            }
        }

        Assertions.assertThat(methodName).isEqualTo("getMethodNameWithThread");
    }

    @DisplayName("StackWalker로 메소드 이름 조회하기 성공")
    @Test
    public void getMethodNameWithStackWalker(){
        String methodName = StackWalker.getInstance()
                .walk(s -> s.skip(0).findFirst())
                .get()
                .getMethodName();

        Assertions.assertThat(methodName).isEqualTo("getMethodNameWithStackWalker");
    }

}
