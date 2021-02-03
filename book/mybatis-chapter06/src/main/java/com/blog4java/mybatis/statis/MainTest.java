package com.blog4java.mybatis.statis;

/**
 * @author : zhuansun
 * @date : 2021-02-03 18:50
 **/
public class MainTest {

    public static void main(String[] args) {

        Lion lion = new Lion();
        lion.setName("狗子");

        Cat proxy = new FeederProxy(lion);

        proxy.eatFood("狗屎");

    }

}
