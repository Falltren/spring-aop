package com.example.springaop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.springaop.service.BookService.get*(..))")
    public void allGetMethods() {
    }

    @Pointcut("execution(* com.example.springaop.service.BookService.add*(..))")
    public void allAddMethods() {
    }

}
