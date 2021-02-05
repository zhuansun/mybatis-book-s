package com.blog4java.mybatis.defaults;

/**
 * @author : zhuansun
 * @date : 2021-02-04 16:36
 **/
public class Lion implements Cat{

    @Override
    public void eat(String food) {
        System.out.println("eat "+ food);
    }
}
