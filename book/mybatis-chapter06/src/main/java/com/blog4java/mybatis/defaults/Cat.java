package com.blog4java.mybatis.defaults;

/**
 * @author : zhuansun
 * @date : 2021-02-04 16:36
 **/
public interface Cat {

    public void eat(String food);


    public default void run(){
        System.out.println("i can run");
    }

}
