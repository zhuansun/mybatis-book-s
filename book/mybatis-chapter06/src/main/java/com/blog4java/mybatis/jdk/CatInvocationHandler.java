package com.blog4java.mybatis.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : zhuansun
 * @date : 2021-02-03 19:08
 **/
@Data
@AllArgsConstructor
public class CatInvocationHandler<Cat> implements InvocationHandler {

    Cat cat;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("动态代理，可以在目标方法执行之前做一些通用的逻辑处理");

        return method.invoke(cat,args);
    }
}
