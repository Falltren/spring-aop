package com.example.springaop.aop;

import com.example.springaop.entity.Book;
import com.example.springaop.util.CustomResponse;
import com.example.springaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Book book = null;
        if (methodSignature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Book) {
                    book = (Book) arg;
                    log.info("Попытка добавить книгу с названием {}", book.getTitle());
                }
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info("Книга с названием {} добавлена", book.getTitle());
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;
        if (methodSignature.getName().equals("getAll")) {
            log.info("Попытка получить все книги");
        } else if (methodSignature.getName().equals("getBookByTitle")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    title = (String) arg;
                    log.info("Попытка получить книгу с названием {}", title);
                }
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
            if (methodSignature.getName().equals("getAll")) {
                log.info("Все книги получены");
            } else if (methodSignature.getName().equals("getBookByTitle")) {
                log.info("Книга с названием {} получена", title);
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        return result;
    }

}
