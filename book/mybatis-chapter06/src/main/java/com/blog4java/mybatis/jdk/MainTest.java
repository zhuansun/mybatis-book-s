package com.blog4java.mybatis.jdk;

import java.lang.reflect.Proxy;

/**
 * @author : zhuansun
 * @date : 2021-02-03 19:09
 **/
public class MainTest {
    public static void main(String[] args) {

        Lion lion = new Lion("二狗子");

        CatInvocationHandler catInvocationHandler = new CatInvocationHandler<Cat>(lion);

        Cat catProxy = (Cat) Proxy.newProxyInstance(Cat.class.getClassLoader(),
            new Class<?>[]{Cat.class},
            catInvocationHandler);

        catProxy.eatFood("屁");
    }

}
