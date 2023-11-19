package com.wandted.matitnyam.util;

public class MethodNameUtil {

    public static String getMethodName(){
        return StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get()
                .getMethodName();
    }
}
